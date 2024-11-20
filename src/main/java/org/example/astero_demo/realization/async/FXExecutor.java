package org.example.astero_demo.realization.async;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum FXExecutor implements AppExecutor {
    INSTANCE;

    private final ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();

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
