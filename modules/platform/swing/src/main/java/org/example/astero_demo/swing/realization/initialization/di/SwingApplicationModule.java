package org.example.astero_demo.swing.realization.initialization.di;

import com.google.inject.*;
import org.example.astero_demo.SwingHelloApplication;
import org.example.astero_demo.realization.context.ops.runtime.Configuration;
import org.example.astero_demo.realization.initialization.launch.Application;
import org.example.astero_demo.HelloApplication;

public class SwingApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HelloApplication.class)
                .annotatedWith(Application.class)
                .to(SwingHelloApplication.class)
                .in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public SwingHelloApplication provideSwingHelloApplicationWrapper(
            final Injector injector, final Configuration configuration) {
        return new SwingHelloApplication(configuration, injector);
    }
}
