package org.example.demo.realization.initialization.di.provider.logic;

import com.google.inject.Inject;
import org.example.demo.core.controller.model.ModelController;
import org.example.demo.core.controller.ui.UIController;
import org.example.demo.core.logic.command.CommandFactoryImpl;
import org.example.demo.realization.initialization.di.provider.InstanceProvider;

/**
 * Instance provider for {@link CommandFactoryImpl}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CommandFactoryProvider extends InstanceProvider<CommandFactoryImpl> {
    private final UIController viewController;
    private final ModelController modelController;

    @Inject
    public CommandFactoryProvider(final UIController viewController, final ModelController modelController) {
        this.viewController = viewController;
        this.modelController = modelController;
    }

    @Override
    protected CommandFactoryImpl createInstance() {
        return new CommandFactoryImpl(viewController, modelController);
    }
}
