package org.example.demo.func;

import com.google.inject.Injector;
import org.example.demo.SwingHelloApplication;
import org.example.demo.functional.TestComponentHolder;
import org.example.demo.realization.context.ops.runtime.Configuration;

/**
 * Test instance that provide access to inner objects
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class SwingTestHelloApplication extends SwingHelloApplication implements TestComponentHolder {

    public SwingTestHelloApplication() {
        // Must be set up later
        super(null, null);
    }

    @Override
    public <T> T getInstance(final Class<T> tClass) {
        return getInjector().getInstance(tClass);
    }

    public void setUp(final Injector injector) {
        this.configuration = injector.getInstance(Configuration.class);
        this.injector = injector;
    }
}
