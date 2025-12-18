package org.example.astero_demo.swing.initialization.multithreading;

import org.example.astero_demo.realization.level.async.RunnableWrapper;

import javax.swing.*;

/**
 * Wrap {@link Runnable} into {@link Task}
 *
 * @author Pilip Yurchanka
 * @since v1.0
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
