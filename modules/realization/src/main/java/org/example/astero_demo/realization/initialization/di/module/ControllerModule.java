package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.state.ModelStateAdapter;
import org.example.astero_demo.core.adapter.ui.CursorLocator;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.controller.keyboard.KeyboardAdapterController;
import org.example.astero_demo.core.controller.keyboard.KeyboardController;
import org.example.astero_demo.core.logic.ClipboardProcessor;
import org.example.astero_demo.core.logic.ShapeProcessor;
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
class ControllerModule extends AbstractModule {

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
    public ModelAdapterController provideModelController(final ModelStateAdapter stateAdapter) {
        return new ModelAdapterController(stateAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public KeyboardController provideKeyboardController(
            final ShapeProcessor shapeProcessor,
            final ClipboardProcessor clipboardProcessor,
            final CursorLocator cursorLocator,
            final ModelState state) {
        return new KeyboardAdapterController(shapeProcessor, clipboardProcessor, cursorLocator, state);
    }
}
