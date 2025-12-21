package org.example.astero_demo.swing.port.keyboard;

import org.example.astero_demo.api.keyboard.Key;
import org.example.astero_demo.core.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.core.port.keyboard.RootShortcutHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Bind keyboard shortcuts with specific operations and delegate it to the {@link OperationAdapter}.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class SwingRootShortcutHandler extends RootShortcutHandler implements KeyListener {

    public SwingRootShortcutHandler(final OperationAdapter keyBoardAdapter) {
        super(keyBoardAdapter);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        process(
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_Z -> Key.Z;
                    case KeyEvent.VK_X -> Key.X;
                    case KeyEvent.VK_C -> Key.C;
                    case KeyEvent.VK_V -> Key.V;
                    case KeyEvent.VK_DELETE -> Key.DELETE;
                    default -> null;
                }
                , keyEvent.isControlDown());
    }

    @Override
    public void keyPressed(final KeyEvent e) {}

    @Override
    public void keyReleased(final KeyEvent e) {}
}
