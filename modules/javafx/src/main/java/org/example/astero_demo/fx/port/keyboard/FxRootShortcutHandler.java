package org.example.astero_demo.fx.port.keyboard;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import org.example.astero_demo.api.keyboard.Key;
import org.example.astero_demo.core.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.core.port.keyboard.RootShortcutHandler;

/**
 * Bind keyboard shortcuts with specific operations and delegate it to the {@link OperationAdapter}.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class FxRootShortcutHandler extends RootShortcutHandler implements EventHandler<KeyEvent> {

    public FxRootShortcutHandler(final OperationAdapter keyBoardAdapter) {
        super(keyBoardAdapter);
    }

    @Override
    public void handle(final KeyEvent keyEvent) {
        process(
                switch (keyEvent.getCode()) {
                    case Z -> Key.Z;
                    case X -> Key.X;
                    case C -> Key.C;
                    case V -> Key.V;
                    case DELETE -> Key.DELETE;
                    default -> null;
        }
        , keyEvent.isControlDown());
    }
}
