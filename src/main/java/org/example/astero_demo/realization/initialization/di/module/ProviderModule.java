package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.realization.initialization.di.provider.controller.ModelControllerProvider;
import org.example.astero_demo.realization.initialization.di.provider.controller.ViewControllerProvider;
import org.example.astero_demo.realization.initialization.di.provider.logic.CommandFactoryProvider;
import org.example.astero_demo.realization.initialization.di.provider.ui.*;
import org.example.astero_demo.realization.initialization.di.provider.ui.adapter.*;

public class ProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        // LOGIC
        bind(CommandFactoryProvider.class).in(Scopes.SINGLETON);

        //CONTROLLER
        bind(ViewControllerProvider.class).in(Scopes.SINGLETON);
        bind(ModelControllerProvider.class).in(Scopes.SINGLETON);

        //UI
        bind(ShortcutHandlerProvider.class).in(Scopes.SINGLETON);
        bind(RootAdapterProvider.class).in(Scopes.SINGLETON);
        bind(LayersPanelAdapterProvider.class).in(Scopes.SINGLETON);
        //bind(LayersTreeProvider.class).in(Scopes.SINGLETON);
    }
}
