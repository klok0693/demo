package org.example.astero_demo.adapter.ui;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import lombok.Setter;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.port.ui.ToolBarView;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolBarAdapter extends UIAdapter implements Initializable {

    public ToolBarView toolBar;
    public Button RectBtn;

    public ToolBarAdapter(final ViewController controller, final UIState uiState) {
        super(controller, uiState);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void update() {}

    @Override
    protected void processEvent(UIEvent event) {
        if (parent != null) {
            parent.processEvent(event);
        }
    }
}
