package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.realization.initialization.di.provider.logic.CommandFactoryProvider;

/**
 * DI config for custom providers
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class ProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        // LOGIC
        bind(CommandFactoryProvider.class).in(Scopes.SINGLETON);
    }
}
