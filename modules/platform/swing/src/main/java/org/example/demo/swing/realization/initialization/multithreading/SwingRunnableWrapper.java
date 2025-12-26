package org.example.demo.swing.realization.initialization.multithreading;

import org.example.demo.realization.level.async.RunnableWrapper;

import javax.swing.*;

/**
 * Wrapped {@link Runnable} into {@link SwingWorker}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingRunnableWrapper implements RunnableWrapper {
    @Override
    public Runnable wrap(final Runnable runnable) {
        return new SwingWorker() {

            @Override
            protected Object doInBackground() {
                runnable.run();
                return null;
            }
        };
    }
}
