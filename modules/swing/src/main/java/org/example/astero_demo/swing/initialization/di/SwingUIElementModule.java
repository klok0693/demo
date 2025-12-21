package org.example.astero_demo.swing.initialization.di;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.ui.property.PropertiesView;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;
import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.core.port.ui.ToolBarPanelView;
import org.example.astero_demo.core.port.ui.canvas.CanvasUI;
import org.example.astero_demo.core.port.ui.elements.LayersTree;
import org.example.astero_demo.swing.port.ui.canvas.background.SwingBackgroundLayer;
import org.example.astero_demo.swing.port.ui.canvas.shape.SwingShapeLayer;
import org.example.astero_demo.swing.port.ui.canvas.tool.SwingToolLayer;
import org.example.astero_demo.swing.port.ui.element.SwingCanvasUI;
import org.example.astero_demo.swing.port.ui.layers.LayersPanelUI;
import org.example.astero_demo.swing.port.ui.layers.SwingLayersPanelUI;
import org.example.astero_demo.swing.port.ui.properties.PropertiesPanelUI;
import org.example.astero_demo.swing.port.ui.properties.SwingPropertiesPanelUI;
import org.example.astero_demo.swing.port.ui.properties.SwingPropertiesPanelView;
import org.example.astero_demo.swing.port.ui.root.RootUI;
import org.example.astero_demo.swing.port.ui.root.SwingRootUI;
import org.example.astero_demo.swing.port.ui.element.SwingLayersTree;
import org.example.astero_demo.swing.port.ui.toolbar.SwingToolBarUI;
import org.example.astero_demo.swing.port.ui.toolbar.ToolBarUI;

/**
 * DI config for UI elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class SwingUIElementModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ToolBarUI.class).to(SwingToolBarUI.class).in(Scopes.SINGLETON);
        bind(RootUI.class).to(SwingRootUI.class).in(Scopes.SINGLETON);
        bind(PropertiesPanelUI.class).to(SwingPropertiesPanelUI.class).in(Scopes.SINGLETON);
        bind(LayersPanelUI.class).to(SwingLayersPanelUI.class).in(Scopes.SINGLETON);

        bind(CanvasUI.class).to(SwingCanvasUI.class);

        bind(LayersTree.class).to(SwingLayersTree.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public SwingRootUI provideRootUI(
            final SwingLayersPanelUI layersPanelUI,
            final SwingPropertiesPanelUI propertiesPanelUI,
            final SwingToolBarUI toolBarUI,
            final SwingCanvasUI canvas,
            final RootView rootView) {
        return new SwingRootUI(layersPanelUI, propertiesPanelUI, toolBarUI, canvas, rootView);
    }

    @Inject
    @Provides
    @Singleton
    public SwingToolBarUI provideToolBarUI(final ToolBarView rootView) {
        return new SwingToolBarUI(rootView);
    }

    @Inject
    @Provides
    @Singleton
    public SwingPropertiesPanelUI providePropertiesPanelUI(final PropertiesView panelView) {
        return new SwingPropertiesPanelUI(panelView);
    }

    @Inject
    @Provides
    @Singleton
    public SwingLayersPanelUI provideLayersPanelUI(final SwingLayersTree layersTree) {
        return new SwingLayersPanelUI(layersTree);
    }


/*    @Inject
    @Provides
    @Singleton
    public LayersTreeBuilder provideLayersTreeBuilder(final SwingLayersTree layersTree) {
        return new LayersTreeBuilder(layersTree);
    }*/

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
    public SwingCanvasUI provideSwingCanvas(
            final SwingBackgroundLayer backgroundLayer,
            final SwingShapeLayer shapeLayer,
            final SwingToolLayer toolLayer) {
        return new SwingCanvasUI(backgroundLayer, shapeLayer, toolLayer);
    }
}
