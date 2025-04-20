package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.logic.EventProcessor;

/**
 * Parent class for all UI adapters
 *
 * @param <T> The type of {@link UIState} that the adapter works with.
 */
public abstract class UIAdapter<T extends UIState> {
    protected final EventProcessor controller;
    protected final T uiState;

    protected UIAdapter(final EventProcessor controller, final T uiState) {
        this.controller = controller;
        this.uiState = uiState;
    }
}
