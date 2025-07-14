package org.example.astero_demo.fx.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.elements.LayersTree;
import org.example.astero_demo.fx.initialization.ui.builder.LayersTreeBuilder;
import org.example.astero_demo.fx.port.ui.element.FxLayersTree;

/**
 * DI config for UI elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class UIElementModule extends AbstractModule {

    @Inject
    @Provides
    @Singleton
    public LayersTreeBuilder provideLayersTreeBuilder(final FxLayersTree layersTree) {
        return new LayersTreeBuilder(layersTree);
    }

    @Inject
    @Provides
    @Singleton
    public FxLayersTree provideLayersTree(
            final ModelState modelState,
            final UIState uiState,
            final LayersAdapter shapeSelector) {
        return new FxLayersTree(modelState, uiState, shapeSelector);
    }
}
