package org.example.astero_demo.adapter.ui.canvas;

import org.example.astero_demo.adapter.ui.event.SelectMultipleElementsEvent;
import org.example.astero_demo.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.event.SelectElementByPositionEvent;
import org.example.astero_demo.adapter.ui.event.SelectNextElementAt;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.EventProcessor;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;

import static org.example.astero_demo.model.metadata.ParamInfo.create;

/**
 * Leaf adapter for interacting with a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ShapeCanvasAdapter extends LeafAdapter implements CanvasAdapter {
    private final CanvasView canvasRoot;

    public ShapeCanvasAdapter(
            final EventProcessor controller,
            final UIState uiState,
            final CanvasView canvasRoot,
            final ParentAdapter parentAdapter) {
        super(controller, uiState, parentAdapter);
        this.canvasRoot = canvasRoot;
    }

    @Override
    public void update() {
        canvasRoot.update();
    }

    @Override
    public void switchMode(final UIMode mode) {
        canvasRoot.switchMode(mode);
    }

    @Override
    public void primaryMouseBtnPressed(final double x, final double y, final boolean isAdditional) {
        if (isAdditional) {
            selectMultiple(x, y);
        }
        else if (!uiState.isInInsertMode()) {
            sendEvent(new SelectElementByPositionEvent(x, y));
        }
    }

    @Override
    public void selectNextShapeAt(double x, double y) {
        if (!uiState.isInInsertMode()) { // TODO: If not necessary - call always from selection tool?
            if (uiState.hasSelectedId()) {
                sendEvent(new SelectNextElementAt(uiState.getSelectedShapeId(), x, y));
            }
            else {
                sendEvent(new SelectElementByPositionEvent(x, y));
            }
        }
    }

    @Override
    public void selectMultiple(double x, double y) {
        sendEvent(new SelectMultipleElementsEvent(x, y));
    }

    @Override
    public void createNewShapeAt(final double x, final double y, final double width, final double height) {
        controller.process(new CreateNewShapeEvent(x, y, width, height, uiState.getInsertShapeType()));
    }

    @Override
    public void modifySelectedShape(final double x, final double y, final double width, final double height) {
        controller.process(new ModifyShapeEvent(
                        uiState.getSelectedShapeId(),
                        create(ShapeParam.X, x),
                        create(ShapeParam.Y, y),
                        create(ShapeParam.WIDTH, width),
                        create(ShapeParam.HEIGHT, height)));
    }

    @Override
    public void moveSelectedShapeTo(final double x, final double y) {
        controller.process(new ModifyShapeEvent(
                uiState.getSelectedShapeId(),
                create(ShapeParam.X, x),
                create(ShapeParam.Y, y)));
    }

    @Override
    public double[] getLocalCursorPosition() {
        return canvasRoot.getLocalCursorPosition();
    }
}