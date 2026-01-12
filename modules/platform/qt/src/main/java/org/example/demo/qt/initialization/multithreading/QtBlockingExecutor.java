package org.example.demo.qt.initialization.multithreading;

import org.example.demo.realization.level.async.BlockingForegroundExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Not necessary for Qt for now
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtBlockingExecutor implements BlockingForegroundExecutor {

    @Override
    public <T> T execute(final Callable<T> supplier) {
        return null;
    }

    @Override
    public void execute(final Runnable command) {
        this.execute(() -> {
            command.run();
            return null;
        });
    }
}
