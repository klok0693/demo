package org.example.astero_demo.realization.async;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface AppExecutor {

    void executeInBackground(Runnable runnable);

    void executeInFXThread(Runnable runnable);
}
