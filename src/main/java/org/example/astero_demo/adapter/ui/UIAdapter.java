package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;

/**
 * Parent class for all UI adapters
 *
 * @param <T> The type of {@link UIState} that the adapter works with.
 */
public abstract class UIAdapter<T extends UIState> {
    protected final LogicEventProcessor controller;
    protected final T uiState;

    protected UIAdapter(final LogicEventProcessor controller, final T uiState) {
        this.controller = controller;
        this.uiState = uiState;
    }
}
