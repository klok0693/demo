package org.example.astero_demo.adapter.ui;

import lombok.Setter;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

public abstract class UIAdapter {
    protected final ViewController controller;

    @Setter
    protected UIAdapter parent;

    protected final UIState uiState;

    public UIAdapter(final ViewController controller, final UIState uiState) {
        this.controller = controller;
        this.uiState = uiState;
    }

    protected abstract void processEvent(UIEvent event);

    public abstract void update();
}
