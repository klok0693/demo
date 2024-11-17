package org.example.astero_demo.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.initialization.di.provider.controller.ModelControllerProvider;
import org.example.astero_demo.initialization.di.provider.controller.ViewControllerProvider;
import org.example.astero_demo.initialization.di.provider.logic.CommandFactoryProvider;
import org.example.astero_demo.initialization.di.provider.ui.*;
import org.example.astero_demo.initialization.di.provider.ui.adapter.*;

public class ProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandFactoryProvider.class).in(Scopes.SINGLETON);

        //CONTROLLER
        bind(ViewControllerProvider.class).in(Scopes.SINGLETON);
        bind(ModelControllerProvider.class).in(Scopes.SINGLETON);

        //UI
        bind(ShortcutProvider.class).in(Scopes.SINGLETON);
        bind(CanvasAdapterProvider.class).in(Scopes.SINGLETON);
        bind(LayersAdapterProvider.class).in(Scopes.SINGLETON);
        bind(PropertyAdapterProvider.class).in(Scopes.SINGLETON);
        bind(ToolBarAdapterProvider.class).in(Scopes.SINGLETON);
        bind(RootAdapterProvider.class).in(Scopes.SINGLETON);
    }
}
