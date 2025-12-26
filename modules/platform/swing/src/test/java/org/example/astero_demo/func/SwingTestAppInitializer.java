package org.example.astero_demo.func;

import com.google.inject.Injector;
import org.example.astero_demo.SwingAppInitializer;
import org.example.astero_demo.SwingHelloApplication;

/**
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class SwingTestAppInitializer extends SwingAppInitializer {
    private final SwingTestHelloApplication application;

    public SwingTestAppInitializer(final SwingTestHelloApplication application) {
        this.application = application;
    }

    @Override
    protected SwingHelloApplication buildApplication(final Injector injector) {
        application.setInjector(injector);
        return application;
    }
}
