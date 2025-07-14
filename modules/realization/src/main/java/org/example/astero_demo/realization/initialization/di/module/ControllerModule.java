package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.model.ModelAdapter;
import org.example.astero_demo.core.logic.ShapeValidator;
import org.example.astero_demo.core.controller.model.ModelAdapterController;
import org.example.astero_demo.core.controller.model.ModelController;
import org.example.astero_demo.core.controller.ui.ControllerAdapter;
import org.example.astero_demo.core.controller.ui.UIAdapterController;
import org.example.astero_demo.core.controller.ui.UIController;

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
