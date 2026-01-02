package org.example.demo.qt.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.adapter.ui.layerspanel.LayersAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.qt.port.ui.canvas.background.QtBackgroundLayer;
import org.example.demo.qt.port.ui.canvas.shape.QtShapeLayer;
import org.example.demo.qt.port.ui.canvas.tool.QtToolLayer;
import org.example.demo.qt.port.ui.element.QtCanvasUI;
import org.example.demo.qt.port.ui.element.QtLayersTree;

/**
 * DI config for UI elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class QtUIElementModule extends AbstractModule {

/*    @Inject
    @Provides
    @Singleton
    public LayersTreeBuilder provideLayersTreeBuilder(final QtLayersTree layersTree) {
        return new LayersTreeBuilder(layersTree);
    }*/

    @Inject
    @Provides
    @Singleton
    public QtLayersTree provideLayersTree(
            final ModelState modelState,
            final UIState uiState,
            final LayersAdapter shapeSelector) {
        return new QtLayersTree(modelState, uiState, shapeSelector);
    }

    @Inject
    @Provides
    @Singleton
    public QtCanvasUI provideFxCanvas(
            final QtBackgroundLayer backgroundLayer,
            final QtShapeLayer shapeLayer,
            final QtToolLayer toolLayer) {
        return new QtCanvasUI(backgroundLayer, shapeLayer, toolLayer);
    }
}
