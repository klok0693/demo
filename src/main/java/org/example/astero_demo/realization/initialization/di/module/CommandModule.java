package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.controller.model.ModelController;
import org.example.astero_demo.controller.ui.UIController;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandFactoryImpl;
import org.example.astero_demo.logic.command.CommandProcessor;

/**
 * DI config for logic commands
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandFactory.class).to(CommandFactoryImpl.class);
        bind(CommandProcessor.class).toInstance(CommandProcessor.INSTANCE);
    }

    @Inject
    @Provides
    @Singleton
    public CommandFactoryImpl provideCommandFactory(
            final UIController viewController,
            final ModelController modelController) {
        return new CommandFactoryImpl(viewController, modelController);
    }
}
