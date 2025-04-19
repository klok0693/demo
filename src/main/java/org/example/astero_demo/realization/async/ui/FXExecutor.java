package org.example.astero_demo.realization.async.ui;

import javafx.application.Platform;

import java.util.concurrent.Executor;

/**
 * Custom executor service, wrapping logic operations into<p>
 * separate non-blocking thread and ui update operations into<p>
 * JavaFX Application thread. Uses virtual non-daemon threads
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class FXExecutor implements Executor {

    @Override
    public void execute(Runnable runnable) {
        Platform.runLater(runnable::run);
    }
}
