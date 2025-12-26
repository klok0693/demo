package org.example.demo.swing.port.keyboard;

import org.example.demo.api.keyboard.Key;
import org.example.demo.core.adapter.keyboard.OperationAdapter;
import org.example.demo.core.port.keyboard.RootShortcutHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Swing realization of {@link RootShortcutHandler}.
 *
 * @author Pilip Yurchanka
 * @since v1.2
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
