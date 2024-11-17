package org.example.astero_demo.initialization.di.provider;

import com.google.inject.Provider;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;

public abstract class InstanceProvider<T> implements Provider<T> {
    protected volatile T instance;

    @Override
    public T get() {
        synchronized (this) {
            if (instance == null) {
                synchronized (InstanceProvider.class) {
                    if (instance == null) {
                        instance = createInstance();
                    }
                }
            }
            return instance;
        }
    }

    protected abstract T createInstance();
}
