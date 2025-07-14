package org.example.astero_demo.fx.port.ui.keyboard;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import org.example.astero_demo.core.adapter.keyboard.OperationAdapter;

/**
 * Bind keyboard shortcuts with specific operations and delegate it to the {@link OperationAdapter}.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class RootShortcutHandler implements EventHandler<KeyEvent> {
    private final OperationAdapter keyBoardAdapter;

    public RootShortcutHandler(final OperationAdapter keyBoardAdapter) {
        this.keyBoardAdapter = keyBoardAdapter;
    }

    @Override
    public void handle(final KeyEvent keyEvent) {
        final boolean isCtrlDown = keyEvent.isControlDown();
        switch (keyEvent.getCode()) {
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
