package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.controller.ViewController;

public abstract class ParentAdapter extends UIAdapter<MutableUIState> {

    public ParentAdapter(final ViewController controller, final MutableUIState uiState) {
        super(controller, uiState);
    }

    protected abstract void processEvent(UIEvent event);
}
