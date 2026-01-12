package org.example.demo.qt.initialization.multithreading;

import org.example.demo.realization.level.async.RunnableWrapper;

/**
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtRunnableWrapper implements RunnableWrapper {
    @Override
    public Runnable wrap(final Runnable runnable) {
        return runnable;
    }
}
