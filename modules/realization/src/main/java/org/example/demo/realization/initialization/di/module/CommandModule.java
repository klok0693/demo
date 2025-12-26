package org.example.demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.demo.core.context.ops.execution.CommandQueue;
import org.example.demo.core.controller.model.ModelController;
import org.example.demo.core.controller.ui.UIController;
import org.example.demo.core.logic.command.CommandFactory;
import org.example.demo.core.logic.command.CommandFactoryImpl;
import org.example.demo.core.logic.command.CommandProcessor;

/**
 * DI config for logic commands
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandFactory.class).to(CommandFactoryImpl.class);
        bind(CommandQueue.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public CommandFactoryImpl provideCommandFactory(
            final UIController viewController,
            final ModelController modelController) {
        return new CommandFactoryImpl(viewController, modelController);
    }

    @Inject
    @Provides
    @Singleton
    public CommandProcessor provideCommandProcessor(final CommandQueue commandsQueue) {
        return new CommandProcessor(commandsQueue);
    }
}
