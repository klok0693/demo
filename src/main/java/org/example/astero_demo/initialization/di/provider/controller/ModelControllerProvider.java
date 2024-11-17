package org.example.astero_demo.initialization.di.provider.controller;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.initialization.di.provider.logic.CommandFactoryProvider;
import org.example.astero_demo.initialization.di.provider.model.ModelAdapterProvider;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;

public class ModelControllerProvider extends ControllerProvider<ModelController> {
    private final ModelAdapterProvider modelAdapterProvider;

    @Inject
    ModelControllerProvider(
            final CommandFactoryProvider commandFactory,
            final CommandProcessor commandProcessor,
            final ModelAdapterProvider modelAdapterProvider) {
        super(commandFactory, commandProcessor);
        this.modelAdapterProvider = modelAdapterProvider;

        commandFactory.setModelController(get());
    }

    @Override
    protected ModelController createInstance() {
        return new ModelController(commandFactory.get(), commandProcessor, modelAdapterProvider.get());
    }
}
