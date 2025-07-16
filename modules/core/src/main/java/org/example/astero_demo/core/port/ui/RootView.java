package org.example.astero_demo.core.port.ui;

import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitchableView;

/**
 * Represents the root view of the application, responsible for managing the main View components.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class RootView implements ModeSwitchableView {
    protected final UIState uiState;

    protected RootView(final UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void update() {}
}
