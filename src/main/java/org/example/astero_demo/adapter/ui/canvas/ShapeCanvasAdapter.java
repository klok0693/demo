package org.example.astero_demo.adapter.ui.canvas;

import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.event.SelectElementByPositionEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;

import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.metadata.ParamInfo.create;

public class ShapeCanvasAdapter extends LeafAdapter implements CanvasAdapter {
    private final CanvasView canvasRoot;

    public ShapeCanvasAdapter(
            final LogicEventProcessor controller,
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
    public void primaryMouseBtnPressed(final double x, final double y) {
        if (!uiState.isInInsertMode()) {
            sendEvent(new SelectElementByPositionEvent(x, y));
        }
    }

    @Override
    public void createNewShapeAt(final double x, final double y, final double width, final double height) {
        controller.process(new CreateNewShapeEvent(x, y, width, height, uiState.getInsertShapeType()));
    }

    @Override
    public void modifySelectedShape(final double x, final double y, final double width, final double height) {
        controller.process(new ModifyShapeEvent(
                        uiState.getSelectedShapeId(),
                        create(ShapeParam.X, valueOf(x)),
                        create(ShapeParam.Y, valueOf(y)),
                        create(ShapeParam.WIDTH, valueOf(width)),
                        create(ShapeParam.HEIGHT, valueOf(height))));
    }

    @Override
    public void moveSelectedShapeTo(final double x, final double y) {
        controller.process(new ModifyShapeEvent(
                uiState.getSelectedShapeId(),
                create(ShapeParam.X, valueOf(x)),
                create(ShapeParam.Y, valueOf(y))));
    }

    @Override
    public double[] getLocalCursorPosition() {
        return canvasRoot.getLocalCursorPosition();
    }
}