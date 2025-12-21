package org.example.astero_demo.core.port.keyboard;

import org.example.astero_demo.api.keyboard.Key;
import org.example.astero_demo.core.adapter.keyboard.OperationAdapter;

import javax.annotation.Nullable;
import java.util.List;

public class RootShortcutHandler implements ShortcutHandler {
    private static final List<Key> SUPPORTED_KEYS = List.of(
            Key.Z,
            Key.X,
            Key.C,
            Key.V,
            Key.DELETE);
    private final OperationAdapter keyBoardAdapter;

    public RootShortcutHandler(final OperationAdapter keyBoardAdapter) {
        this.keyBoardAdapter = keyBoardAdapter;
    }

    @Override
    public void process(@Nullable final Key key, final boolean isCtrlDown) {
        if (key == null  || !SUPPORTED_KEYS.contains(key)) {
            return;
        }

        switch (key) {
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
            case X:
                if (isCtrlDown) {
                    keyBoardAdapter.handleCut();
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
