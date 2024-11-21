package org.example.astero_demo.realization.async;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public enum FXExecutor implements AppExecutor {
    INSTANCE;
    private final ThreadFactory virtualFactory = Thread.ofVirtual().factory();

    private final ExecutorService service;

    FXExecutor() {
        this.service = Executors.newThreadPerTaskExecutor(runnable -> {
            final Thread thread = virtualFactory.newThread(runnable);
            thread.setName("Background " + runnable.hashCode());
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
