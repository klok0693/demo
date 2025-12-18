package org.example.astero_demo.fx.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import org.example.astero_demo.fx.initialization.ui.CustomControllerFactory;
import org.example.astero_demo.fx.initialization.ui.NodeBuilderFactory;

/**
 * DI config for javafx factories
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class FxInitializationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Callback.class).to(CustomControllerFactory.class);
        bind(CustomControllerFactory.class).in(Scopes.SINGLETON);

        bind(BuilderFactory.class).to(NodeBuilderFactory.class);
        bind(NodeBuilderFactory.class).in(Scopes.SINGLETON);
    }
}
