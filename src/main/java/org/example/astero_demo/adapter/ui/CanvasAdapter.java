package org.example.astero_demo.adapter.ui;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.example.astero_demo.adapter.model.ParamInfo;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.event.SelectElementEvent;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ApplicationEvent;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;
import org.example.astero_demo.port.ui.canvas.CanvasView;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.ParamInfo.create;

public class CanvasAdapter extends LeafAdapter implements CanvasView.CanvasDelegate {
    public CanvasView canvas;
    public AnchorPane canvasRoot;

    private final StateHolder holder;

    public CanvasAdapter(final ViewController controller, final StateHolder holder, final UIState uiState) {
        super(controller, uiState);
        this.holder = holder;
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        //canvas.redraw();

        canvas.setDelegate(this);
        canvas.setUiState(uiState);
    }

    @Override
    public void update() {
        canvas.update(holder, uiState.isInInsertMode() || !uiState.hasSelectedId());
    }

    @Override
    public void primaryMouseBtnPressed(final double x, final double y) {
        if (uiState.isInInsertMode()) {
            controller.process(new CreateNewShapeEvent(x, y));
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
        controller.process(new ModifyShapeEvent(
                uiState.getSelectedShapeId(),
                create(ShapeParam.X, valueOf(x)),
                create(ShapeParam.Y, valueOf(y))));
    }

    @Override
    public void onDragOver(final double x, final double y, final double width, final double height) {
        controller.process(new ModifyShapeEvent(
                uiState.getSelectedShapeId(),
                create(ShapeParam.X, valueOf(x)),
                create(ShapeParam.Y, valueOf(y)),
                create(ShapeParam.WIDTH, valueOf(width)),
                create(ShapeParam.HEIGHT, valueOf(height))));
    }

    public ShapeElement selectElement(final double x, final double y) {
        return canvas.selectElement(x, y);
    }
}
