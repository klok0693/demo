package org.example.astero_demo.adapter.ui;

import javafx.fxml.Initializable;
import lombok.Getter;
import lombok.Setter;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ApplicationEvent;

public abstract class UIAdapter<T extends UIState> {
    protected final ViewController controller;
    protected final T uiState;

    protected UIAdapter(final ViewController controller, final T uiState) {
        this.controller = controller;
        this.uiState = uiState;
    }
}
