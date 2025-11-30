package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.clipboard.ClipboardAdapter;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.logic.*;
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
