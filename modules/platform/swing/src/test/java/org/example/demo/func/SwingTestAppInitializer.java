package org.example.demo.func;

import com.google.inject.Injector;
import org.example.demo.SwingAppInitializer;
import org.example.demo.SwingHelloApplication;

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
        application.setUp(injector);
        return application;
    }
}
