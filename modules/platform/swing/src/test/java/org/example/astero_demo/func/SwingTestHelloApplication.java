package org.example.astero_demo.func;

import com.google.inject.Injector;
import org.example.astero_demo.SwingHelloApplication;
import org.example.astero_demo.functional.TestComponentHolder;

/**
 * Test instance that provide access to inner objects
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class SwingTestHelloApplication extends SwingHelloApplication implements TestComponentHolder {

    public SwingTestHelloApplication() {
        // Must be set up later
        super(null);
    }

    @Override
    public <T> T getInstance(final Class<T> tClass) {
        return getInjector().getInstance(tClass);
    }

    public void setInjector(final Injector injector) {
        this.injector = injector;
    }
}
