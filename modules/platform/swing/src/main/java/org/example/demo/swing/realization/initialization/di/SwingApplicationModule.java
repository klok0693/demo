package org.example.demo.swing.realization.initialization.di;

import com.google.inject.*;
import org.example.demo.SwingHelloApplication;
import org.example.demo.HelloApplication;
import org.example.demo.realization.context.ops.runtime.Configuration;
import org.example.demo.realization.initialization.launch.Application;

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
