package org.example.astero_demo.swing.initialization.di;

import com.google.inject.*;
import javafx.event.EventHandler;
import org.example.astero_demo.core.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersView;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.core.adapter.ui.property.PropertiesView;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.core.port.ui.LayersPanelView;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;
import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.core.port.ui.ToolBarView;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.core.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.swing.port.ui.SwingPropertiesPanelView;
import org.example.astero_demo.swing.port.ui.SwingRootView;
import org.example.astero_demo.swing.port.ui.SwingToolBarView;
import org.example.astero_demo.swing.port.ui.canvas.SwingShapeCanvasView;
import org.example.astero_demo.swing.port.ui.canvas.background.SwingBackgroundLayer;
import org.example.astero_demo.swing.port.ui.canvas.shape.SwingShapeLayer;
import org.example.astero_demo.swing.port.ui.canvas.tool.draggable.selection.SwingShapeSelectionTool;
import org.example.astero_demo.swing.port.ui.canvas.tool.SwingToolLayer;
import org.example.astero_demo.swing.port.ui.canvas.tool.draggable.drag.SwingDragShapeTool;
import org.example.astero_demo.swing.port.ui.canvas.tool.draggable.insert.SwingInsertShapeTool;
import org.example.astero_demo.swing.port.ui.element.SwingCanvas;
import org.example.astero_demo.swing.port.ui.keyboard.SwingRootShortcutHandler;
import org.example.astero_demo.swing.initialization.ui.builder.CanvasBuilder;

/**
 * DI config for UI views
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class UIViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventHandler.class).to(SwingRootShortcutHandler.class);
        bind(CanvasView.class).to(ShapeCanvasView.class);
        bind(ShapeCanvasView.class).to(SwingShapeCanvasView.class).in(Scopes.SINGLETON);
        bind(LayersView.class).to(LayersPanelView.class);
        bind(PropertiesView.class).to(PropertiesPanelView.class);
        bind(BackgroundLayer.class).to(SwingBackgroundLayer.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public CanvasBuilder provideCanvasBuilder(final SwingCanvas canvas) {
        return new CanvasBuilder(canvas);
    }

    @Inject
    @Provides
    @Singleton
    public SwingShapeCanvasView provideCanvasView(
            final UIState state,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final SwingBackgroundLayer backgroundLayer,
            final SwingShapeLayer shapeLayer,
            final SwingToolLayer toolLayer) {
        return new SwingShapeCanvasView(state, modelState, adapter, backgroundLayer, shapeLayer, toolLayer);
    }

    @Inject
    @Provides
    @Singleton
    public SwingDragShapeTool provideDragTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        return new SwingDragShapeTool(adapter, modelState, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public SwingShapeSelectionTool provideSelectionTool(final CanvasAdapter adapter, final ModelState modelState, final UIState uiState) {
        return new SwingShapeSelectionTool(modelState, uiState, adapter);
    }

    @Inject
    @Provides
    @Singleton
    public SwingInsertShapeTool provideInsertTool(final CanvasAdapter adapter, final UIState uiState) {
        return new SwingInsertShapeTool(adapter, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public SwingShapeLayer provideShapeLayer(final ModelState modelState) {
        return new SwingShapeLayer(modelState);
    }

    @Inject
    @Provides
    @Singleton
    public SwingToolLayer provideToolLayer(
            final SwingShapeSelectionTool selectionTool,
            final SwingDragShapeTool dragShapeTool,
            final SwingInsertShapeTool insertTool,
            final UIState uiState) {
        return new SwingToolLayer(selectionTool, dragShapeTool, insertTool, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public RootView provideRootView(final EventHandler shortcutHandler, final UIState uiState) {
        return new SwingRootView(shortcutHandler, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public LayersPanelView provideLayersPanelView() {
        return new LayersPanelView();
    }

    @Inject
    @Provides
    @Singleton
    public PropertiesPanelView providePropertyPanelView(
            final PropertiesAdapter propertyUpdatable,
            final UIState uiState) {
        return new SwingPropertiesPanelView(propertyUpdatable, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarView provideToolBarView(final UIState uiState, final ToolBarAdapter operationProcessor) {
        return new SwingToolBarView(uiState, operationProcessor); /*new ToolBarView(uiState, operationProcessor);*/
    }

    @Inject
    @Provides
    @Singleton
    public SwingRootShortcutHandler provideShortcutHandler(final OperationAdapter adapter) {
        return new SwingRootShortcutHandler(adapter);
    }
}
