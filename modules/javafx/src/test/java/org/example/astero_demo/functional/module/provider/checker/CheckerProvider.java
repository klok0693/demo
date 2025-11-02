package org.example.astero_demo.functional.module.provider.checker;

import com.google.inject.Provider;
import org.example.astero_demo.functional.TestComponentHolder;

public abstract class CheckerProvider<T> implements Provider<T> {
    protected final TestComponentHolder holder;

    protected CheckerProvider(final TestComponentHolder holder) {
        this.holder = holder;
    }
}
