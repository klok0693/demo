package org.example.demo.qt.initialization.multithreading;

import org.example.demo.realization.level.async.RunnableWrapper;

/**
 * Wrap {@link Runnable} into {@link Task}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class QtRunnableWrapper implements RunnableWrapper {
    @Override
    public Runnable wrap(final Runnable runnable) {
        return runnable;
    }
}
