package org.example.astero_demo.swing.initialization.multithreading;

import org.example.astero_demo.realization.level.async.NonBlockingForegroundExecutor;

import javax.swing.*;

/**
 * Custom executor service, wrapping logic operations into<p>
 * separate non-blocking thread and ui update operations into<p>
 * JavaFX Application thread.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class SwingNonBlockingExecutor implements NonBlockingForegroundExecutor {

    @Override
    public void execute(final Runnable runnable) {
        SwingUtilities.invokeLater(runnable::run);
    }
}
