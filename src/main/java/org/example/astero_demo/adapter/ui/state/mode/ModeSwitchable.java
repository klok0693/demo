package org.example.astero_demo.adapter.ui.state.mode;

public interface ModeSwitchable extends ModeSwitcher {

    @Override
    default void switchMode(final UIMode mode) {
        mode.visit(this);
    }

    void switchMode();
}
