package org.example.astero_demo.fx.initialization.di;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.core.port.keyboard.RootShortcutHandler;
import org.example.astero_demo.core.port.ui.LayersPanelView;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;
import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.core.port.ui.ToolBarPanelView;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.core.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.fx.port.ui.FxLayersPanelView;
import org.example.astero_demo.fx.port.ui.FxPropertiesPanelView;
import org.example.astero_demo.fx.port.ui.FxRootView;
import org.example.astero_demo.fx.port.ui.FxToolBarView;
import org.example.astero_demo.fx.port.ui.canvas.FxShapeCanvasView;
import org.example.astero_demo.fx.port.ui.canvas.background.FxBackgroundLayer;
import org.example.astero_demo.fx.port.ui.canvas.shape.FxShapeLayer;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.selection.FxShapeSelectionTool;
import org.example.astero_demo.fx.port.ui.canvas.tool.FxToolLayer;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.drag.FxDragShapeTool;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.insert.FxInsertShapeTool;
import org.example.astero_demo.fx.port.ui.element.FxCanvasUI;
import org.example.astero_demo.fx.port.ui.element.FxLayersTree;
import org.example.astero_demo.fx.port.keyboard.FxRootShortcutHandler;
import org.example.astero_demo.fx.initialization.ui.builder.CanvasBuilder;

/**
 * DI config for UI views
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class FxViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ShapeCanvasView.class).to(FxShapeCanvasView.class).in(Scopes.SINGLETON);
        bind(BackgroundLayer.class).to(FxBackgroundLayer.class).in(Scopes.SINGLETON);
        bind(LayersPanelView.class).to(FxLayersPanelView.class).in(Scopes.SINGLETON);

        bind(RootShortcutHandler.class).to(FxRootShortcutHandler.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public CanvasBuilder provideCanvasBuilder(final FxCanvasUI canvas) {
        return new CanvasBuilder(canvas);
    }

    @Inject
    @Provides
    @Singleton
    public FxShapeCanvasView provideCanvasView(
            final UIState state,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final FxBackgroundLayer backgroundLayer,
            final FxShapeLayer shapeLayer,
            final FxToolLayer toolLayer,
            final FxCanvasUI canvasUI) {
        return new FxShapeCanvasView(state, modelState, adapter, backgroundLayer, shapeLayer, toolLayer, canvasUI);
    }

    @Inject
    @Provides
    @Singleton
    public FxDragShapeTool provideDragTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        return new FxDragShapeTool(adapter, modelState, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public FxShapeSelectionTool provideSelectionTool(final CanvasAdapter adapter, final ModelState modelState, final UIState uiState) {
        return new FxShapeSelectionTool(modelState, uiState, adapter);
    }

    @Inject
    @Provides
    @Singleton
    public FxInsertShapeTool provideInsertTool(final CanvasAdapter adapter, final UIState uiState) {
        return new FxInsertShapeTool(adapter, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public FxShapeLayer provideShapeLayer(final ModelState modelState) {
        return new FxShapeLayer(modelState);
    }

    @Inject
    @Provides
    @Singleton
    public FxToolLayer provideToolLayer(
            final FxShapeSelectionTool selectionTool,
            final FxDragShapeTool dragShapeTool,
            final FxInsertShapeTool insertTool,
            final UIState uiState) {
        return new FxToolLayer(selectionTool, dragShapeTool, insertTool, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public RootView provideRootView(final UIState uiState) {
        return new FxRootView(uiState);
    }

    @Inject
    @Provides
    @Singleton
    public PropertiesPanelView providePropertyPanelView(
            final PropertiesAdapter propertyUpdatable,
            final UIState uiState) {
        return new FxPropertiesPanelView(propertyUpdatable, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarPanelView provideToolBarView(final UIState uiState, final ToolBarAdapter operationProcessor) {
        return new FxToolBarView(uiState, operationProcessor); /*new ToolBarView(uiState, operationProcessor);*/
    }

    @Inject
    @Provides
    @Singleton
    public FxRootShortcutHandler provideShortcutHandler(final OperationAdapter adapter) {
        return new FxRootShortcutHandler(adapter);
    }

    @Inject
    @Provides
    @Singleton
    public FxLayersPanelView provideLayersPanelView(final FxLayersTree layersTree) {
        return new FxLayersPanelView(layersTree);
    }
}
