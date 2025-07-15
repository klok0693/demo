package org.example.astero_demo.realization.level.async.logic;

/**
 * TODO: to wrap GUI threads
 */
public interface RunnableWrapper {

    Runnable wrap(Runnable runnable);
}
