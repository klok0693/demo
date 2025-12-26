package org.example.demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.demo.core.adapter.clipboard.ClipboardAdapter;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.logic.LogicClipboardProcessor;
import org.example.demo.core.logic.LogicShapeProcessor;
import org.example.demo.core.logic.ShapeProcessor;
import org.example.demo.core.logic.ShapeValidator;
import org.example.demo.core.logic.command.CommandFactory;
import org.example.demo.core.logic.command.CommandProcessor;

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

    @Inject
    @Provides
    @Singleton
    public LogicClipboardProcessor provideClipboardProcessor(
            final ClipboardAdapter adapter,
            final ShapeProcessor shapeProcessor,
            final ModelState modelState) {
        return new LogicClipboardProcessor(adapter, shapeProcessor, modelState);
    }
}
