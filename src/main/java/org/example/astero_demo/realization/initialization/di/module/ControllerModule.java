package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.controller.model.ModelAdapterController;
import org.example.astero_demo.controller.model.ModelController;
import org.example.astero_demo.controller.ui.ControllerAdapter;
import org.example.astero_demo.controller.ui.UIAdapterController;
import org.example.astero_demo.controller.ui.UIController;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;

/**
 * DI config for controllers
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ShapeValidator.class).in(Scopes.SINGLETON);
        bind(ModelController.class).to(ModelAdapterController.class);
        bind(UIController.class).to(UIAdapterController.class);
    }

    @Inject
    @Provides
    @Singleton
    public UIAdapterController provideViewController(final ControllerAdapter adapter) {
        return new UIAdapterController(adapter);
    }


    @Inject
    @Provides
    @Singleton
    public ModelAdapterController provideModelController(final ModelAdapter modelAdapter) {
        return new ModelAdapterController(modelAdapter);
    }
}
