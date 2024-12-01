package org.example.astero_demo.adapter.ui.state.mode;

public interface MultipleSelectionModeSwitchable extends ModeSwitchable {

    @Override
    default void switchMode() {
        switchToMultipleSelectionMode();
    }

    void switchToMultipleSelectionMode();
}
