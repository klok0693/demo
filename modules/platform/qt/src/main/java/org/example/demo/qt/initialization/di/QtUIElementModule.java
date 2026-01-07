package org.example.demo.qt.initialization.di;

import com.google.inject.*;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.adapter.ui.layerspanel.LayersAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.elements.LayersTree;
import org.example.demo.qt.port.ui.canvas.background.QtBackgroundLayer;
import org.example.demo.qt.port.ui.canvas.shape.QtShapeLayer;
import org.example.demo.qt.port.ui.canvas.tool.QtToolLayer;
import org.example.demo.qt.port.ui.element.QtCanvasUI;
import org.example.demo.qt.port.ui.element.QtLayersTree;
import org.example.demo.qt.port.ui.graphics.QtPainterFactory;

/**
 * DI config for UI elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class QtUIElementModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LayersTree.class).to(QtLayersTree.class).in(Scopes.SINGLETON);
    }

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

    @Inject
    @Provides
    @Singleton
    public QtPainterFactory providePainterFactory() {
        return new QtPainterFactory();
    }
}
