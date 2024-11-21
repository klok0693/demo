package org.example.astero_demo.port.ui.keyboard;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.astero_demo.adapter.keyboard.OperationAdapter;

public class RootShortcutHandler implements EventHandler<KeyEvent> {
    private final OperationAdapter keyBoardAdapter;

    public RootShortcutHandler(final OperationAdapter keyBoardAdapter) {
        this.keyBoardAdapter = keyBoardAdapter;
    }

    @Override
    public void handle(final KeyEvent keyEvent) {
        handle(keyEvent.getCode(), keyEvent.isControlDown());
    }

    public void handle(final KeyCode keyCode, final boolean isCtrlDown) {
        switch (keyCode) {
            case DELETE:
                keyBoardAdapter.handleDelete();
                break;
            case Z:
                if (isCtrlDown) {
                    keyBoardAdapter.handleUndo();
                }
                break;
            case C:
                if (isCtrlDown) {
                    keyBoardAdapter.handleCopy();
                }
                break;
            case V:
                if (isCtrlDown) {
                    keyBoardAdapter.handlePaste();
                }
                break;
        }
    }
}
