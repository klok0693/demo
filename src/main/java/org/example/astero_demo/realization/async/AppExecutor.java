package org.example.astero_demo.realization.async;

import java.util.concurrent.Executor;

public interface AppExecutor {

    void executeInBackground(Runnable runnable);

    void executeInFXThread(Runnable runnable);
}
