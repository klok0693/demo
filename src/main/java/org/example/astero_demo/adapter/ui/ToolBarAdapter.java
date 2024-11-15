package org.example.astero_demo.adapter.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.adapter.ui.event.InsertModeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.port.ui.ToolBarView;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolBarAdapter extends LeafAdapter {

    public ToolBarView toolBar;
    public ToggleButton RectBtn;

    public ToolBarAdapter(final ViewController controller, final UIState uiState) {
        super(controller, uiState);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void update() {}

    public void onInsertRectAction(final ActionEvent event) {
        sendEvent(new InsertModeEvent(ShapeType.RECT));
    }

    public void onInsertCycleAction(final ActionEvent event) {
        sendEvent(new InsertModeEvent(ShapeType.OVAL));
    }

    public void onDeleteAction(final ActionEvent event) {
        if (uiState.hasSelectedId()) {
            controller.process(new RemoveShapeEvent(uiState.getSelectedShapeId()));
        }
    }
}
