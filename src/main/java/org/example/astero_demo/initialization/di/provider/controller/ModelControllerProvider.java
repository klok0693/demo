package org.example.astero_demo.initialization.di.provider.controller;

import com.google.inject.Inject;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.initialization.di.provider.logic.CommandFactoryProvider;
import org.example.astero_demo.logic.command.CommandProcessor;

public class ModelControllerProvider extends ControllerProvider<ModelController> {

    @Inject
    ModelControllerProvider(final CommandFactoryProvider commandFactory, final CommandProcessor commandProcessor) {
        super(commandFactory, commandProcessor);
        commandFactory.setModelController(this);
    }

    @Override
    protected ModelController createInstance() {
        return new ModelController(commandFactory.get(), commandProcessor);
    }
}
