package org.example.astero_demo.adapter.ui.state.mode;

public interface InsertModeSwitchable extends ModeSwitchable {

    @Override
    default void switchMode() {
        switchToInsertMode();
    }

    void switchToInsertMode();
}
