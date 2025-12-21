package org.example.astero_demo.fx.initialization.multithreading;

import javafx.application.Platform;
import org.example.astero_demo.realization.level.async.BlockingForegroundExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class FxBlockingExecutor implements BlockingForegroundExecutor {

    @Override
    public <T> T execute(final Callable<T> supplier) {
/*        if (Platform.isFxApplicationThread()) {
            return supplier.get();
        }*/

        final CompletableFuture<T> future = new CompletableFuture<>();
        Platform.runLater(() -> {
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
