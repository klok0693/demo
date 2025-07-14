package org.example.astero_demo.fx.initialization.di;

import com.google.inject.*;
import javafx.event.EventHandler;
import org.example.astero_demo.core.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersView;
import org.example.astero_demo.core.model.state.ModelState;
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
import org.example.astero_demo.core.port.ui.canvas.shape.ShapeLayer;
import org.example.astero_demo.core.port.ui.canvas.tool.ShapeSelectionTool;
import org.example.astero_demo.core.port.ui.canvas.tool.ToolLayer;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.drag.DragShapeTool;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert.InsertTool;
import org.example.astero_demo.fx.port.ui.FxToolBarView;
import org.example.astero_demo.core.port.ui.keyboard.RootShortcutHandler;
import org.example.astero_demo.fx.initialization.ui.builder.CanvasBuilder;

/**
 * DI config for UI views
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class UIViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventHandler.class).to(RootShortcutHandler.class);
        bind(CanvasView.class).to(ShapeCanvasView.class);
        bind(LayersView.class).to(LayersPanelView.class);
        bind(PropertiesView.class).to(PropertiesPanelView.class);
        bind(BackgroundLayer.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public CanvasBuilder provideCanvasBuilder(final ShapeCanvasView canvasView) {
        return new CanvasBuilder(canvasView);
    }

    @Inject
    @Provides
    @Singleton
    public ShapeCanvasView provideCanvasView(
            final UIState state,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final BackgroundLayer backgroundLayer,
            final ShapeLayer shapeLayer,
            final ToolLayer toolLayer) {
        return new ShapeCanvasView(state, modelState, adapter, backgroundLayer, shapeLayer, toolLayer);
    }

    @Inject
    @Provides
    @Singleton
    public DragShapeTool provideDragTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        return new DragShapeTool(adapter, modelState, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public ShapeSelectionTool provideSelectionTool(final CanvasAdapter adapter, final ModelState modelState, final UIState uiState) {
        return new ShapeSelectionTool(adapter, modelState, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public InsertTool provideInsertTool(final CanvasAdapter adapter, final UIState uiState) {
        return new InsertTool(adapter, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public ShapeLayer provideShapeLayer(final ModelState modelState) {
        return new ShapeLayer(modelState);
    }

    @Inject
    @Provides
    @Singleton
    public ToolLayer provideToolLayer(
            final ShapeSelectionTool selectionTool,
            final DragShapeTool dragShapeTool,
            final InsertTool insertTool,
            final UIState uiState) {
        return new ToolLayer(selectionTool, dragShapeTool, insertTool, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public RootView provideRootView(final EventHandler shortcutHandler, final UIState uiState) {
        return new RootView(shortcutHandler, uiState);
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
        return new PropertiesPanelView(propertyUpdatable, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarView provideToolBarView(final UIState uiState, final ToolBarAdapter operationProcessor) {
        return new FxToolBarView(uiState, operationProcessor); /*new ToolBarView(uiState, operationProcessor);*/
    }

    @Inject
    @Provides
    @Singleton
    public RootShortcutHandler provideShortcutHandler(final OperationAdapter adapter) {
        return new RootShortcutHandler(adapter);
    }
}
