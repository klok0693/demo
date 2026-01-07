package org.example.demo.fx.initialization.di;

import com.google.inject.*;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.adapter.ui.layerspanel.LayersAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.elements.LayersTree;
import org.example.demo.fx.initialization.ui.builder.LayersTreeBuilder;
import org.example.demo.fx.port.ui.canvas.background.FxBackgroundLayer;
import org.example.demo.fx.port.ui.canvas.shape.FxShapeLayer;
import org.example.demo.fx.port.ui.canvas.tool.FxToolLayer;
import org.example.demo.fx.port.ui.element.FxCanvasUI;
import org.example.demo.fx.port.ui.element.FxLayersTree;

/**
 * DI config for UI elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class FxUIElementModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LayersTree.class).to(FxLayersTree.class).in(Scopes.SINGLETON);
    }

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

    @Inject
    @Provides
    @Singleton
    public FxCanvasUI provideFxCanvas(
            final FxBackgroundLayer backgroundLayer,
            final FxShapeLayer shapeLayer,
            final FxToolLayer toolLayer) {
        return new FxCanvasUI(backgroundLayer, shapeLayer, toolLayer);
    }
}
