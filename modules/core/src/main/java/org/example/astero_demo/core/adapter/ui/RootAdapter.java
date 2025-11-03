package org.example.astero_demo.core.adapter.ui;

import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.core.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.core.logic.ShapeProcessor;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.entity.ShapeType;
import org.example.astero_demo.core.model.metadata.dto.ShapeParams;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.event.*;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.core.adapter.ui.state.MutableUIState;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.core.controller.ui.ControllerAdapter;
import org.example.astero_demo.core.port.ui.RootView;

import java.util.Optional;
import java.util.function.Supplier;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Parent adapter for all leafs adapters. The only component in UI block,<p>
 * able to change {@link org.example.astero_demo.adapter.ui.state.UIState}.<p>
 * Provide communication between ui controller and other adapters<p>
 * Also process all {@link UIEvent}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class RootAdapter extends UIAdapter<MutableUIState> implements ParentAdapter, ControllerAdapter {
    private final ModelState modelState;
    private final ToolBarAdapter toolBarAdapter;
    private final CanvasAdapter canvasAdapter;
    private final PropertiesAdapter propertyAdapter;
    private final LayersAdapter layersAdapter;

    private final RootView rootView;

    public RootAdapter(
            final ModelState modelState,
            final ShapeProcessor controller,
            final MutableUIState uiState,
            final RootView rootView,
            final CanvasAdapter canvasAdapter,
            final LayersAdapter layersAdapter,
            final PropertiesAdapter propertyAdapter,
            final ToolBarAdapter toolBarAdapter) {
        super(controller, uiState);
        this.modelState = modelState;
        this.rootView = rootView;
        this.canvasAdapter = canvasAdapter;
        this.layersAdapter = layersAdapter;
        this.propertyAdapter = propertyAdapter;
        this.toolBarAdapter = toolBarAdapter;
    }

    @Override
    public void onCreateUpdate(final int id) {
        selectElement(id);
    }

    @Override
    public void onModifyUpdate(final int id) {
        selectElement(id);
    }

    @Override
    public void onRemoveUpdate() {
        uiState.reset();
        updateChildren();
    }

    @Override
    public void processEvent(final UIEvent event) { // TODO: move to separate class
        if (event instanceof final SelectElementByPositionEvent e) {
            selectElement(e.getX(), e.getY());
        }
        else if (event instanceof final SelectElementById e) {
            selectElement(e.getSelectedId());
        }
        if (event instanceof final SelectNextElementAt e) {
            selectNextElement(e.getCurrentId(), e.getX(), e.getY());
        }
        if (event instanceof final SelectMultipleElementsEvent e) {
            selectMultipleElement(e.getX(), e.getY());
        }
        else if (event instanceof final InsertModeEvent e) {
            uiState.setInsertShapeType(e.getInsertShapeType());
            switchMode(uiState.getMode());
        }
        else if (event instanceof final CopyShapeEvent e) {
            uiState.storeCopyOf(uiState.getSelectedShapeId());
        }
        else if (event instanceof final PasteShapeEvent e) {
            final double[] currentPosition = canvasAdapter.getLocalCursorPosition();
            controller.createShape(new ShapeParams(
                    parseInt(uiState.getCopyPriority()),
                    currentPosition[0],
                    currentPosition[1],
                    parseDouble(uiState.getCopyWidth()),
                    parseDouble(uiState.getCopyHeight()),
                    parseInt(uiState.getCopyColor()),
                    ShapeType.valueOf(uiState.getCopyType()))
            );
        }
    }

    private void selectElement(final int id) {
        selectElement(() -> modelState.getShape(id));
    }

    private void selectElement(final double shapeX, final double shapeY) {
        selectElement(() -> modelState.findTopShapeAt(shapeX, shapeY).orElse(null));
    }

    private void selectElement(final Supplier<Shape> shapeSupplier) {
        final Shape selectedShape = shapeSupplier.get();
        final Integer shapeId = selectedShape != null ? selectedShape.getId() : null;

        uiState.setSelectShape(shapeId);
        switchMode(uiState.getMode());
    }

    private void selectNextElement(final int id, final double x, final double y) {
        final Shape nextShape = modelState.findNextShapeAt(id, x, y);
        if (nextShape != null) {
            selectElement(nextShape.getId());
        }
        else {
            uiState.reset();
            updateChildren();
        }
    }

    private void selectMultipleElement(final double x, final double y) {
        final int firstSelectedId = uiState.getSelectedShapeId();
        final Optional<Shape> newSelectedShape = modelState.findTopShapeAt(x, y);

        uiState.reset();
        updateChildren();
        if (newSelectedShape.isEmpty()) {
            return;
        }
        uiState.setMultipleSelectedShapes(firstSelectedId, newSelectedShape.get().getId());
        switchMode(uiState.getMode());
    }

    private void switchMode(final UIMode newMode) {
        canvasAdapter.switchMode(newMode);
        toolBarAdapter.switchMode(newMode);
        layersAdapter.switchMode(newMode);
        propertyAdapter.switchMode(newMode);
        rootView.switchMode(newMode);

        updateChildren();
    }

    private void updateChildren() {
        log.debug("Updating ui root children");
        toolBarAdapter.update();
        canvasAdapter.update();
        propertyAdapter.update();
        layersAdapter.update();
        rootView.update();
    }
}
