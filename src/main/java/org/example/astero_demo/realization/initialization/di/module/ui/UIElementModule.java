package org.example.astero_demo.realization.initialization.di.module.ui;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.elements.LayersTree;
import org.example.astero_demo.realization.initialization.ui.builder.LayersTreeBuilder;

public class UIElementModule extends AbstractModule {

    @Override
    protected void configure() {

    }

    @Inject
    @Provides
    @Singleton
    public LayersTreeBuilder provideLayersTreeBuilder(final LayersTree layersTree) {
        return new LayersTreeBuilder(layersTree);
    }

    @Inject
    @Provides
    @Singleton
    public LayersTree provideLayersTree(
            final ModelState modelState,
            final UIState uiState,
            final LayersAdapter shapeSelector) {
        return new LayersTree(modelState, uiState, shapeSelector);
    }
}
