package org.example.astero_demo.adapter.ui;

import javafx.geometry.Point2D;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.event.*;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.controller.ui.ControllerAdapter;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.port.ui.RootView;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;

import java.awt.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class RootAdapter extends UIAdapter<MutableUIState> implements ParentAdapter, ControllerAdapter {
    public ToolBarAdapter toolBarAdapter;
    public CanvasAdapter canvasAdapter;
    public PropertiesAdapter propertyAdapter;
    public LayersAdapter layersAdapter;
    public RootView rootView;

    public ShapeCanvasView canvasRoot;

    public RootAdapter(
            final LogicEventProcessor controller,
            final MutableUIState uiState,
            final RootView rootView,
            final CanvasAdapter canvasAdapter,
            final LayersAdapter layersAdapter,
            final PropertiesAdapter propertyAdapter,
            final ToolBarAdapter toolBarAdapter) {
        super(controller, uiState);
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
        uiState.setIsInInsertMode(false);
        uiState.removeSelection();
        updateChildren();
    }

    private void updateChildren() {
        toolBarAdapter.update();
        canvasAdapter.update();
        propertyAdapter.update();
        layersAdapter.update();
        rootView.update();
    }

    @Override
    public void processEvent(final UIEvent event) {
        if (event instanceof final SelectElementByPositionEvent e) {
            selectElement(e.getX(), e.getY());
        }
        else if (event instanceof final SelectElementById e) {
            selectElement(e.getSelectedId());
        }
        else if (event instanceof final InsertModeEvent e) {
            uiState.setIsInInsertMode(true);
            uiState.setInsertShapeType(e.getInsertShapeType());
            updateChildren();
        }
        else if (event instanceof final CopyShapeEvent e) {
            uiState.storeCopyOf(uiState.getSelectedShapeId());
        }
        else if (event instanceof final PasteShapeEvent e) {
            final Point cursorPosition = MouseInfo.getPointerInfo().getLocation(); // Get cursor position on screen
            final Point2D localPosition = canvasRoot.screenToLocal(cursorPosition.getX(), cursorPosition.getY());

            updateChildren();

            controller.process(new CreateNewShapeEvent(
                    parseInt(uiState.getCopyPriority()),
                    localPosition.getX(),
                    localPosition.getY(),
                    parseDouble(uiState.getCopyWidth()),
                    parseDouble(uiState.getCopyHeight()),
                    parseInt(uiState.getCopyColor()),
                    ShapeType.valueOf(uiState.getCopyType()))
            );
        }
    }

    private void selectElement(final int id) {
        uiState.setIsInInsertMode(false);
        updateChildren();

        final Shape selectedShape = canvasAdapter.selectElement(id);
        final Integer shapeId = selectedShape != null ? selectedShape.getId() : null;

        uiState.setSelectShape(shapeId);
        updateChildren();
    }

    private void selectElement(final double shapeX, final double shapeY) {
        uiState.setIsInInsertMode(false);
        updateChildren();

        final Shape selectedShape = canvasAdapter.selectElement(shapeX, shapeY);
        final Integer shapeId = selectedShape != null ? selectedShape.getId() : null;

        uiState.setSelectShape(shapeId);
        updateChildren();
    }
}
