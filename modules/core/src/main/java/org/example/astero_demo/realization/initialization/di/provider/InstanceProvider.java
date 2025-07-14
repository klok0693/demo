package org.example.astero_demo.realization.initialization.di.provider;

import com.google.inject.Provider;

/**
 * Although the DI library is quite handy, sometimes there is<p>
 * a need for more flexible customisation of the objects created.<p>
 * In this case, instance providers are used
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
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
