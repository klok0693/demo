package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.controller.ViewController;

public abstract class ParentAdapter extends UIAdapter<MutableUIState> {

    protected ParentAdapter(final ViewController controller, final MutableUIState uiState) {
        super(controller, uiState);
    }

    public abstract void processEvent(UIEvent event);
}
