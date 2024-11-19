package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.ui.event.SelectElementByPositionEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.metadata.ParamInfo.create;

public class ShapeCanvasAdapter extends LeafAdapter implements CanvasAdapter {
    public final CanvasView canvasRoot;

    public ShapeCanvasAdapter(final ViewController controller, final UIState uiState, final CanvasView canvasRoot) {
        super(controller, uiState);
        this.canvasRoot = canvasRoot;
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {}

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
        update();
        controller.process(
                new CreateNewShapeEvent(x, y, width, height, uiState.getInsertShapeType()));
    }

    @Override
    public void modifySelectedShape(final double x, final double y, final double width, final double height) {
        update();
        controller.process(
                new ModifyShapeEvent(
                        uiState.getSelectedShapeId(),
                        create(ShapeParam.X, valueOf(x)),
                        create(ShapeParam.Y, valueOf(y)),
                        create(ShapeParam.WIDTH, valueOf(width)),
                        create(ShapeParam.HEIGHT, valueOf(height))));
    }

    @Override
    public void moveSelectedShapeTo(final double x, final double y) {
        update();
        controller.process(new ModifyShapeEvent(
                uiState.getSelectedShapeId(),
                create(ShapeParam.X, valueOf(x)),
                create(ShapeParam.Y, valueOf(y))));
    }

    @Override
    public Shape selectElement(final double x, final double y) {
        return canvasRoot.selectElement(x, y);
    }

    @Override
    public Shape selectElement(final int id) {
        return canvasRoot.selectElement(id);
    }
}