package org.example.astero_demo.fx.initialization.multithreading;

import javafx.application.Platform;
import org.example.astero_demo.realization.level.async.ui.ForegroundExecutor;

/**
 * Custom executor service, wrapping logic operations into<p>
 * separate non-blocking thread and ui update operations into<p>
 * JavaFX Application thread.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class FxExecutor implements ForegroundExecutor {

    @Override
    public void execute(final Runnable runnable) {
        Platform.runLater(runnable::run);
    }
}
