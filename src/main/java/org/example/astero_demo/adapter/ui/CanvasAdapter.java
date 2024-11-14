package org.example.astero_demo.adapter.ui;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.event.SelectElementEvent;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ApplicationEvent;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.port.ui.canvas.CanvasView;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

import java.net.URL;
import java.util.ResourceBundle;

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
    }

    @Override
    public void update() {
        canvas.update(holder, uiState.isInInsertMode() || !uiState.hasSelectedId());
    }

    @Override
    public void primaryMouseBtnClicked(final int priority, final double x, final double y) {
        if (uiState.isInInsertMode()) {
            controller.process(new CreateNewShapeEvent(priority, x, y));
        }
        else if (canvas.hasAnyElement(x, y)) {
            sendEvent(new SelectElementEvent(x, y));
        }
        else {
            sendEvent(new SelectElementEvent(-1, -1));
        }
    }

    public ShapeElement selectElement(final double x, final double y) {
        return canvas.selectElement(x, y);
    }
}
