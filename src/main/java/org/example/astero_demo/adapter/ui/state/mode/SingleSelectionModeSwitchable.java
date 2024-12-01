package org.example.astero_demo.adapter.ui.state.mode;

public interface SingleSelectionModeSwitchable extends ModeSwitchable {

    @Override
    default void switchMode() {
        switchToSingleSelectionMode();
    }

    void switchToSingleSelectionMode();
}
