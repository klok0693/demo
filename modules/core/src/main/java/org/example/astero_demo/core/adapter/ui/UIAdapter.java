package org.example.astero_demo.core.adapter.ui;

import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.logic.ShapeProcessor;

/**
 * Parent class for all UI adapters
 *
 * @param <T> The type of {@link UIState} that the adapter works with.
 */
public abstract class UIAdapter<T extends UIState> {
    protected final ShapeProcessor processor;
    protected final T uiState;

    protected UIAdapter(final ShapeProcessor processor, final T uiState) {
        this.processor = processor;
        this.uiState = uiState;
    }
}
