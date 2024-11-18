package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.model.Shape;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.event.SelectElementEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;
import org.example.astero_demo.port.ui.canvas.CanvasView;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.ParamInfo.create;

public class CanvasAdapter extends LeafAdapter implements CanvasView.CanvasDelegate {
    public CanvasView canvasRoot;

    public CanvasAdapter(final ViewController controller, final UIState uiState) {
        super(controller, uiState);
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        //canvas.redraw();
        canvasRoot.requestFocus();
    }

    @Override
    public void update() {
        canvasRoot.update(uiState.isInInsertMode() || !uiState.hasSelectedId());
    }

    @Override
    public void primaryMouseBtnPressed(final double x, final double y) {
        if (uiState.isInInsertMode()) {
            //sendEvent(new SelectElementEvent(-1, -1));
            //controller.process(new CreateNewShapeEvent(x, y));
        }
        else {
            sendEvent(new SelectElementEvent(x, y));
        }
/*        else if (canvas.hasAnyElement(x, y)) {
            sendEvent(new SelectElementEvent(x, y));
        }
        else {
            sendEvent(new SelectElementEvent(-1, -1));
        }*/
    }

    @Override
    public void onDragOver(final double x, final double y) {
        update();

        final LogicEvent event = uiState.isInInsertMode() ?
                new CreateNewShapeEvent(x, y, 100, 100, uiState.getInsertShapeType()) :
                new ModifyShapeEvent(
                        uiState.getSelectedShapeId(),
                        create(ShapeParam.X, valueOf(x)),
                        create(ShapeParam.Y, valueOf(y)));

        controller.process(event);
    }

    @Override
    public void onDragOver(final double x, final double y, final double width, final double height) {
        update();

        final LogicEvent event = uiState.isInInsertMode() ?
                new CreateNewShapeEvent(x, y, width, height, uiState.getInsertShapeType()) :
                new ModifyShapeEvent(
                uiState.getSelectedShapeId(),
                create(ShapeParam.X, valueOf(x)),
                create(ShapeParam.Y, valueOf(y)),
                create(ShapeParam.WIDTH, valueOf(width)),
                create(ShapeParam.HEIGHT, valueOf(height)));

        controller.process(event);
    }

    public Shape selectElement(final double x, final double y) {
        return canvasRoot.selectElement(x, y);
    }
}
