package org.example.demo.swing.realization.initialization.multithreading;

import org.example.demo.realization.level.async.NonBlockingForegroundExecutor;

import javax.swing.*;

/**
 * Swing realization of {@link NonBlockingForegroundExecutor}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingNonBlockingExecutor implements NonBlockingForegroundExecutor {

    @Override
    public void execute(final Runnable runnable) {
        SwingUtilities.invokeLater(runnable::run);
    }
}
