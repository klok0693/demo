package org.example.demo.qt.initialization.di;

import com.google.inject.*;
import org.example.demo.core.adapter.keyboard.OperationAdapter;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.demo.core.port.keyboard.RootShortcutHandler;
import org.example.demo.core.port.ui.LayersPanelView;
import org.example.demo.core.port.ui.PropertiesPanelView;
import org.example.demo.core.port.ui.RootView;
import org.example.demo.core.port.ui.ToolBarPanelView;
import org.example.demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.demo.core.port.ui.canvas.background.BackgroundLayer;
import org.example.demo.qt.port.ui.QtLayersPanelView;
import org.example.demo.qt.port.ui.QtPropertiesPanelView;
import org.example.demo.qt.port.ui.QtRootView;
import org.example.demo.qt.port.ui.QtToolBarView;
import org.example.demo.qt.port.ui.canvas.QtShapeCanvasView;
import org.example.demo.qt.port.ui.canvas.background.QtBackgroundLayer;
import org.example.demo.qt.port.ui.canvas.shape.QtShapeLayer;
import org.example.demo.qt.port.ui.canvas.tool.draggable.selection.QtShapeSelectionTool;
import org.example.demo.qt.port.ui.canvas.tool.QtToolLayer;
import org.example.demo.qt.port.ui.canvas.tool.draggable.drag.QtDragShapeTool;
import org.example.demo.qt.port.ui.canvas.tool.draggable.insert.QtInsertShapeTool;
import org.example.demo.qt.port.ui.element.QtCanvasUI;
import org.example.demo.qt.port.ui.element.QtLayersTree;
import org.example.demo.qt.port.keyboard.QtRootShortcutHandler;

/**
 * DI config for UI views
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
class QtViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ShapeCanvasView.class).to(QtShapeCanvasView.class).in(Scopes.SINGLETON);
        bind(BackgroundLayer.class).to(QtBackgroundLayer.class).in(Scopes.SINGLETON);
        bind(LayersPanelView.class).to(QtLayersPanelView.class).in(Scopes.SINGLETON);

        bind(RootShortcutHandler.class).to(QtRootShortcutHandler.class).in(Scopes.SINGLETON);
    }

/*
    @Inject
    @Provides
    @Singleton
    public CanvasBuilder provideCanvasBuilder(final QtCanvasUI canvas) {
        return new CanvasBuilder(canvas);
    }
*/

    @Inject
    @Provides
    @Singleton
    public QtShapeCanvasView provideCanvasView(
            final UIState state,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final QtBackgroundLayer backgroundLayer,
            final QtShapeLayer shapeLayer,
            final QtToolLayer toolLayer,
            final QtCanvasUI canvasUI) {
        return new QtShapeCanvasView(state, modelState, adapter, backgroundLayer, shapeLayer, toolLayer, canvasUI);
    }

    @Inject
    @Provides
    @Singleton
    public QtDragShapeTool provideDragTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        return new QtDragShapeTool(adapter, modelState, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public QtShapeSelectionTool provideSelectionTool(final CanvasAdapter adapter, final ModelState modelState, final UIState uiState) {
        return new QtShapeSelectionTool(modelState, uiState, adapter);
    }

    @Inject
    @Provides
    @Singleton
    public QtInsertShapeTool provideInsertTool(final CanvasAdapter adapter, final UIState uiState) {
        return new QtInsertShapeTool(adapter, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public QtShapeLayer provideShapeLayer(final ModelState modelState) {
        return new QtShapeLayer(modelState);
    }

    @Inject
    @Provides
    @Singleton
    public QtToolLayer provideToolLayer(
            final QtShapeSelectionTool selectionTool,
            final QtDragShapeTool dragShapeTool,
            final QtInsertShapeTool insertTool,
            final UIState uiState) {
        return new QtToolLayer(selectionTool, dragShapeTool, insertTool, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public RootView provideRootView(final UIState uiState) {
        return new QtRootView(uiState);
    }

    @Inject
    @Provides
    @Singleton
    public PropertiesPanelView providePropertyPanelView(
            final PropertiesAdapter propertyUpdatable,
            final UIState uiState) {
        return new QtPropertiesPanelView(propertyUpdatable, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarPanelView provideToolBarView(final UIState uiState, final ToolBarAdapter operationProcessor) {
        return new QtToolBarView(uiState, operationProcessor); /*new ToolBarView(uiState, operationProcessor);*/
    }

    @Inject
    @Provides
    @Singleton
    public QtRootShortcutHandler provideShortcutHandler(final OperationAdapter adapter) {
        return new QtRootShortcutHandler(adapter);
    }

    @Inject
    @Provides
    @Singleton
    public QtLayersPanelView provideLayersPanelView(final QtLayersTree layersTree) {
        return new QtLayersPanelView(layersTree);
    }
}
