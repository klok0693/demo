package org.example.astero_demo.realization.initialization.di.module.ui;

import com.google.inject.*;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.adapter.ui.property.PropertyPanelUpdatable;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.toolbar.OperationProcessor;
import org.example.astero_demo.port.ui.LayersPanelView;
import org.example.astero_demo.port.ui.PropertyPanelView;
import org.example.astero_demo.port.ui.RootView;
import org.example.astero_demo.port.ui.ToolBarView;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.port.ui.canvas.tool.ShapeSelectionTool;
import org.example.astero_demo.port.ui.canvas.tool.ToolLayer;
import org.example.astero_demo.port.ui.canvas.tool.draggable.DragShapeTool;
import org.example.astero_demo.port.ui.canvas.tool.draggable.InsertTool;
import org.example.astero_demo.port.ui.elements.LayersTree;
import org.example.astero_demo.realization.initialization.ui.builder.CanvasBuilder;

public class UIViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CanvasView.class).to(ShapeCanvasView.class);
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
            final ToolLayer toolLayer) {
        return new ShapeCanvasView(state, modelState, adapter, backgroundLayer, toolLayer);
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
    public LayersPanelView provideLayersPanel() {
        return new LayersPanelView();
    }

    @Inject
    @Provides
    @Singleton
    public RootView provideRootView(final RootShortcutHandler shortcutHandler) {
        return new RootView(shortcutHandler);
    }

    @Inject
    @Provides
    @Singleton
    public PropertyPanelView providePropertyPanel(
            final PropertyPanelUpdatable propertyUpdatable,
            final UIState uiState) {
        return new PropertyPanelView(propertyUpdatable, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarView provideToolBarView(final UIState uiState, final OperationProcessor operationProcessor) {
        return new ToolBarView(uiState, operationProcessor);
    }
}
