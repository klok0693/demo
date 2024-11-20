package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;

public abstract class UIAdapter<T extends UIState> {
    protected final LogicEventProcessor controller;
    protected final T uiState;

    protected UIAdapter(final LogicEventProcessor controller, final T uiState) {
        this.controller = controller;
        this.uiState = uiState;
    }
}
