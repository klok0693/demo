package org.example.astero_demo.adapter.ui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import lombok.Setter;
import org.example.astero_demo.adapter.ui.event.InsertModeEvent;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ApplicationEvent;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.port.ui.ToolBarView;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolBarAdapter extends UIAdapter<UIState> implements Initializable {

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

    public void onInsertButtonAction(final ActionEvent event) {
        processEvent(new InsertModeEvent());
    }

    @Override
    protected void processEvent(final UIEvent event) {
        if (parent != null) {
            parent.processEvent(event);
        }
    }

    public void onDeleteAction(final ActionEvent event) {
        if (uiState.hasSelectedId()) {
            controller.process(new RemoveShapeEvent(uiState.getSelectedShapeId()));
        }
    }
}
