package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import org.example.astero_demo.realization.initialization.di.module.ui.UIAdapterModule;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new AsyncModule());
        install(new ProviderModule());
        install(new ModelModule());
        install(new CommandModule());
        install(new ControllerModule());
        install(new LogicModule());
        install(new UIAdapterModule());
    }
}
