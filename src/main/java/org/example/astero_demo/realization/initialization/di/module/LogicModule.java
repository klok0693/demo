package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.logic.LogicEventProcessor;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;

public class LogicModule extends AbstractModule {

    @Inject
    @Provides
    @Singleton
    public LogicEventProcessor provideEventProcessor(
            final CommandFactory commandFactory,
            final CommandProcessor commandProcessor,
            final ShapeValidator validator) {
        return new LogicEventProcessor(commandFactory, commandProcessor, validator);
    }
}
