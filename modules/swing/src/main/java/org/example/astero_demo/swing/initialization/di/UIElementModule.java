package org.example.astero_demo.swing.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.swing.initialization.ui.builder.LayersTreeBuilder;
import org.example.astero_demo.swing.port.ui.element.SwingCanvas;
import org.example.astero_demo.swing.port.ui.element.SwingLayersTree;

/**
 * DI config for UI elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class UIElementModule extends AbstractModule {

    @Inject
    @Provides
    @Singleton
    public LayersTreeBuilder provideLayersTreeBuilder(final SwingLayersTree layersTree) {
        return new LayersTreeBuilder(layersTree);
    }

    @Inject
    @Provides
    @Singleton
    public SwingLayersTree provideLayersTree(
            final ModelState modelState,
            final UIState uiState,
            final LayersAdapter shapeSelector) {
        return new SwingLayersTree(modelState, uiState, shapeSelector);
    }

    @Inject
    @Provides
    @Singleton
    public SwingCanvas provideFxCanvas() {
        return new SwingCanvas();
    }
}
