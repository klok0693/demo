package org.example.astero_demo.adapter.ui;

import lombok.Setter;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

public abstract class LeafAdapter extends UIAdapter<UIState> {
    @Setter
    protected ParentAdapter parent;

    public LeafAdapter(final ViewController controller, final UIState uiState) {
        super(controller, uiState);
    }

    protected void sendEvent(final UIEvent event) {
        parent.processEvent(event);
    }
}
