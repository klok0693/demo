package org.example.demo.fx.initialization.multithreading;

import javafx.concurrent.Task;
import org.example.demo.realization.level.async.RunnableWrapper;

/**
 * Wrap {@link Runnable} into {@link Task}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class FxRunnableWrapper implements RunnableWrapper {
    @Override
    public Runnable wrap(final Runnable runnable) {
        return new Task<Void>() {
            @Override
            protected Void call() {
                runnable.run();
                return null;
            }
        };
    }
}
