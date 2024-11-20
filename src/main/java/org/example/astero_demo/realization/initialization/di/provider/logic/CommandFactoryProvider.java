package org.example.astero_demo.realization.initialization.di.provider.logic;

import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.realization.initialization.di.provider.InstanceProvider;
import org.example.astero_demo.logic.command.CommandFactoryImpl;

public class CommandFactoryProvider extends InstanceProvider<CommandFactoryProvider.CommandFactoryProxy> {
    private ViewController viewController;
    private ModelController modelController;

    public CommandFactoryProvider() {
        get();
    }

    @Override
    protected CommandFactoryProxy createInstance() {
        final CommandFactoryProxy proxy = new CommandFactoryProxy();
        if (viewController != null) {
            proxy.setViewController(viewController);
        }
        if (modelController != null) {
            proxy.setModelController(modelController);
        }
        return proxy;
    }

    public void setViewController(final ViewController controller) {
        if (instance != null) {
            this.instance.setViewController(controller);
        }
        else {
            this.viewController = controller;
        }
    }

    public void setModelController(final ModelController modelController) {
        if (instance != null) {
            this.instance.setModelController(modelController);
        }
        else {
            this.modelController = modelController;
        }
    }

    static class CommandFactoryProxy extends CommandFactoryImpl {

        public CommandFactoryProxy() {
            super(null, null);
        }

        void setViewController(final ViewController controller) {
            this.viewController = controller;
        }

        void setModelController(final ModelController controller) {
            this.modelController = controller;
        }
    }
}
