package org.example.astero_demo.initialization.di.provider.controller;

import com.google.inject.Inject;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.initialization.di.provider.logic.CommandFactoryProvider;
import org.example.astero_demo.logic.command.CommandProcessor;

public class ViewControllerProvider extends ControllerProvider<ViewController> {
    private final ShapeValidator validator;

    @Inject
    ViewControllerProvider(
            final CommandFactoryProvider commandFactory,
            final CommandProcessor commandProcessor,
            final ShapeValidator validator) {
        super(commandFactory, commandProcessor);
        this.validator = validator;

        commandFactory.setViewController(this);
    }


    @Override
    protected ViewController createInstance() {
        return new ViewController(commandFactory.get(), commandProcessor, validator);
    }
}
