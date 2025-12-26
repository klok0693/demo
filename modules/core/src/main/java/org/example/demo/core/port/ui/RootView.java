package org.example.demo.core.port.ui;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.state.mode.ModeSwitchableView;
import org.example.demo.core.port.ui.model.Cursors;

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

    @Override
    public void switchToInsertMode() {
        setCursor(Cursors.CROSSHAIR);
    }

    @Override
    public void switchToSingleSelectionMode() {
        setCursor(Cursors.DEFAULT);
    }

    @Override
    public void switchToMultipleSelectionMode() {
        setCursor(Cursors.DEFAULT);
    }

    protected abstract void setCursor(Cursors cursor);
}
