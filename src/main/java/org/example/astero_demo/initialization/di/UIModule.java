package org.example.astero_demo.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.ui.*;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.UIStateHolder;
import org.example.astero_demo.initialization.di.provider.ui.*;
import org.example.astero_demo.initialization.di.provider.ui.adapter.*;

public class UIModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UIState.class).to(MutableUIState.class);
        bind(MutableUIState.class).to(UIStateHolder.class).in(Scopes.SINGLETON);
        bind(UIStateHolder.class).toProvider(UIStateHolderProvider.class).in(Scopes.SINGLETON);

        bind(RootShortcutHandler.class).toProvider(ShortcutHandlerProvider.class).asEagerSingleton();

        bind(LayersAdapter.class).toProvider(LayersAdapterProvider.class).in(Scopes.SINGLETON);
        bind(PropertyAdapter.class).toProvider(PropertyAdapterProvider.class).in(Scopes.SINGLETON);
        bind(CanvasAdapter.class).toProvider(CanvasAdapterProvider.class).in(Scopes.SINGLETON);
        bind(ToolBarAdapter.class).toProvider(ToolBarAdapterProvider.class).in(Scopes.SINGLETON);

        bind(ParentAdapter.class).to(RootAdapter.class);
        bind(RootAdapter.class).toProvider(RootAdapterProvider.class).asEagerSingleton();
    }
}
