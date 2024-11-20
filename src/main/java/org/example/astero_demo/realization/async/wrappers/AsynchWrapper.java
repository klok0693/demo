package org.example.astero_demo.realization.async.wrappers;

import lombok.Getter;
import org.example.astero_demo.realization.async.AppExecutor;

import java.util.Collections;
import java.util.List;

public abstract class AsynchWrapper<T> {
    private final AppExecutor executor;
    @Getter
    protected final T wrappedElement;

    protected AsynchWrapper(final AppExecutor executor, final T wrappedElement) {
        this.executor = executor;
        this.wrappedElement = wrappedElement;
    }

    protected void executeInBackground(final Runnable runnable) {
        executor.executeInBackground(runnable);
    }

    protected void executeInFXThread(final Runnable runnable) {
        executor.executeInFXThread(runnable);
    }

    public boolean hasInjectedField(final String fieldName) {
        return false;
    }
}
