package org.example.astero_demo.realization.initialization.di.provider.controller;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.realization.initialization.di.provider.logic.CommandFactoryProvider;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandFactoryImpl;
import org.example.astero_demo.logic.command.CommandProcessor;

public class ViewControllerProvider extends ControllerProvider<ViewControllerProvider.ViewControllerProxy> {
    private final ShapeValidator validator;

    @Inject
    ViewControllerProvider(
            final CommandFactoryProvider commandFactory,
            final CommandProcessor commandProcessor,
            final ShapeValidator validator) {
        super(commandFactory, commandProcessor);
        this.validator = validator;

        commandFactory.setViewController(get());
    }

    public void setRootAdapter(final RootAdapter adapter) {
        this.instance.setRootAdapter(adapter);
    }

    @Override
    protected ViewControllerProxy createInstance() {
        return new ViewControllerProxy(commandFactory.get(), commandProcessor, validator);
    }

    static class ViewControllerProxy extends ViewController {

        public ViewControllerProxy(CommandFactory commandFactory, CommandProcessor commandProcessor, ShapeValidator validator) {
            super(commandFactory, commandProcessor, null, validator);
        }

        void setRootAdapter(final RootAdapter adapter) {
            this.adapter = adapter;
        }
    }
}
