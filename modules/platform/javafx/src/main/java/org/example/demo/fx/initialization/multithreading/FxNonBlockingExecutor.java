package org.example.demo.fx.initialization.multithreading;

import javafx.application.Platform;
import org.example.demo.realization.level.async.NonBlockingForegroundExecutor;

/**
 * Custom executor service, wrapping logic operations into<p>
 * separate non-blocking thread and ui update operations into<p>
 * JavaFX Application thread.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class FxNonBlockingExecutor implements NonBlockingForegroundExecutor {

    @Override
    public void execute(final Runnable runnable) {
        Platform.runLater(runnable::run);
    }
}
