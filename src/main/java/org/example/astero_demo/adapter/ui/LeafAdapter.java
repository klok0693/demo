package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

public abstract class LeafAdapter extends UIAdapter<UIState> implements UpdatableAdapter {
    protected final ParentAdapter parent;

    protected LeafAdapter(
            final ViewController controller,
            final UIState uiState,
            final ParentAdapter parentAdapter) {
        super(controller, uiState);
        parent = parentAdapter;
    }

    protected void sendEvent(final UIEvent event) {
        parent.processEvent(event);
    }
}
