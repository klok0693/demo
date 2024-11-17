package org.example.astero_demo.initialization.di.provider.logic;

import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.initialization.di.provider.InstanceProvider;
import org.example.astero_demo.initialization.di.provider.controller.ModelControllerProvider;
import org.example.astero_demo.initialization.di.provider.controller.ViewControllerProvider;
import org.example.astero_demo.logic.command.CommandFactoryImpl;

public class CommandFactoryProvider extends InstanceProvider<CommandFactoryProvider.CommandFactoryProxy> {

    @Override
    protected CommandFactoryProxy createInstance() {
        return new CommandFactoryProxy();
    }

    public void setViewController(final ViewControllerProvider viewController) {
        this.instance.setViewController(viewController.get());
    }

    public void setModelController(final ModelControllerProvider modelController) {
        this.instance.setModelController(modelController.get());
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
