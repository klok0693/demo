package org.example.astero_demo.fx.initialization.multithreading;

import javafx.concurrent.Task;
import org.example.astero_demo.realization.level.async.logic.RunnableWrapper;

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
