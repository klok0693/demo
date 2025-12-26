package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new AsyncModule());
        install(new ConfigurationModule());
        install(new ProviderModule());
        install(new ContextModule());
        install(new CommandModule());
        install(new ControllerModule());
        install(new LogicModule());
        install(new UIAdapterModule());
        install(new ViewModule());
    }
}
