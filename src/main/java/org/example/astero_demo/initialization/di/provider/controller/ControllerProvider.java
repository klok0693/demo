package org.example.astero_demo.initialization.di.provider.controller;

import org.example.astero_demo.controller.AbstractController;
import org.example.astero_demo.initialization.di.provider.InstanceProvider;
import org.example.astero_demo.initialization.di.provider.logic.CommandFactoryProvider;
import org.example.astero_demo.logic.command.CommandProcessor;

abstract class ControllerProvider<T extends AbstractController> extends InstanceProvider<T> {
    protected final CommandFactoryProvider commandFactory;
    protected final CommandProcessor commandProcessor;

    ControllerProvider(
            final CommandFactoryProvider commandFactory,
            final CommandProcessor commandProcessor) {
        this.commandFactory = commandFactory;
        this.commandProcessor = commandProcessor;
    }
}
