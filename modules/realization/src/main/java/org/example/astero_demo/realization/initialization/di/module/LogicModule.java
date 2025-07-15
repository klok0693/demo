package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.core.logic.ShapeValidator;
import org.example.astero_demo.core.logic.LogicShapeProcessor;
import org.example.astero_demo.core.logic.command.CommandFactory;
import org.example.astero_demo.core.logic.command.CommandProcessor;

class LogicModule extends AbstractModule {

    @Inject
    @Provides
    @Singleton
    public LogicShapeProcessor provideEventProcessor(
            final CommandFactory commandFactory,
            final CommandProcessor commandProcessor,
            final ShapeValidator validator) {
        return new LogicShapeProcessor(commandFactory, commandProcessor, validator);
    }
}
