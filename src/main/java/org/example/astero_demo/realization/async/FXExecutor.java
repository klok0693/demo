package org.example.astero_demo.realization.async;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum FXExecutor implements AppExecutor {
    INSTANCE;

    private final ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();

    @Override
    public void execute(final Runnable command) {
        service.execute(new Task<Void>() {
            @Override
            protected Void call() {
                command.run();
                return null;
            }
        });
    }
}
