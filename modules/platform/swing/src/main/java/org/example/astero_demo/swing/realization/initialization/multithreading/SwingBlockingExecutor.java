package org.example.astero_demo.swing.realization.initialization.multithreading;

import org.example.astero_demo.realization.level.async.BlockingForegroundExecutor;

import javax.swing.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Swing realization of {@link BlockingForegroundExecutor}
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class SwingBlockingExecutor implements BlockingForegroundExecutor {

    @Override
    public <T> T execute(final Callable<T> supplier) {
        final CompletableFuture<T> future = new CompletableFuture<>();
        // not invokeAndWait()!
        SwingUtilities.invokeLater(() -> {
            try {
                future.complete(supplier.call());
            } catch (final Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future.join();
    }

    @Override
    public void execute(final Runnable command) {
        this.execute(() -> {
            command.run();
            return null;
        });
    }
}
