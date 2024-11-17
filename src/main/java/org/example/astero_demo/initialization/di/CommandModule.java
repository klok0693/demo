package org.example.astero_demo.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.initialization.di.provider.logic.CommandFactoryProvider;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandFactoryImpl;
import org.example.astero_demo.logic.command.CommandProcessor;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandFactory.class).to(CommandFactoryImpl.class);
        bind(CommandFactoryImpl.class).toProvider(CommandFactoryProvider.class).asEagerSingleton();
        bind(CommandProcessor.class).toInstance(CommandProcessor.INSTANCE);
    }
}
