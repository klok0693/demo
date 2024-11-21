package org.example.astero_demo.realization.async;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Custom executor service, wrapping logic operations into<p>
 * separate non-blocking thread and ui update operations into<p>
 * JavaFX Application thread. Uses virtual non-daemon threads
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public enum FXExecutor implements AppExecutor {
    INSTANCE;

    private static final String THREAD_NAME_PREFIX = "Background ";

    private final ThreadFactory virtualFactory = Thread.ofVirtual().factory();
    private final ExecutorService service;

    FXExecutor() {
        this.service = Executors.newThreadPerTaskExecutor(runnable -> {
            final Thread thread = virtualFactory.newThread(runnable);
            thread.setName(THREAD_NAME_PREFIX + runnable.hashCode());
            return thread;
        });
    }

    @Override
    public void executeInBackground(final Runnable runnable) {
        service.execute(new Task<Void>() {
            @Override
            protected Void call() {
                runnable.run();
                return null;
            }
        });
    }

    @Override
    public void executeInFXThread(final Runnable runnable) {
        Platform.runLater(runnable::run);
    }
}
