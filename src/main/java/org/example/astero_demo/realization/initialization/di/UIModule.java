package org.example.astero_demo.realization.initialization.di;

import com.google.inject.*;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.*;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.adapter.ui.canvas.ShapeCanvasAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersPanelAdapter;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.UIStateHolder;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.port.ui.LayersPanel;
import org.example.astero_demo.port.ui.elements.LayersTree;
import org.example.astero_demo.realization.initialization.di.provider.ui.*;
import org.example.astero_demo.realization.initialization.di.provider.ui.adapter.*;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.port.ui.canvas.tool.draggable.DragShapeTool;
import org.example.astero_demo.port.ui.canvas.tool.draggable.InsertTool;
import org.example.astero_demo.port.ui.canvas.tool.ShapeSelectionTool;
import org.example.astero_demo.port.ui.canvas.tool.ToolLayer;
import org.example.astero_demo.realization.initialization.ui.builder.CanvasBuilder;
import org.example.astero_demo.realization.initialization.ui.builder.LayersPanelBuilder;
import org.example.astero_demo.realization.initialization.ui.builder.LayersTreeBuilder;

public class UIModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UIState.class).to(MutableUIState.class);
        bind(MutableUIState.class).to(UIStateHolder.class).in(Scopes.SINGLETON);

        bind(RootShortcutHandler.class).toProvider(ShortcutHandlerProvider.class).asEagerSingleton();
        bind(RootAdapter.class).toProvider(RootAdapterProvider.class).asEagerSingleton();

        //bind(LayersTree.class).toProvider(LayersTreeProvider.class).asEagerSingleton();
        bind(LayersPanelAdapter.class).toProvider(LayersPanelAdapterProvider.class).asEagerSingleton();

        bind(CanvasView.class).to(ShapeCanvasView.class);
        bind(ParentAdapter.class).to(RootAdapter.class);
        bind(BackgroundLayer.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public UIStateHolder provideUIHolder(final ModelState modelState) {
        return new UIStateHolder(modelState);
    }

    @Inject
    @Provides
    @Singleton
    public PropertyPanelAdapter providePropertyAdapter(
            final ViewController controller,
            final UIState uiState) {
        return new PropertyPanelAdapter(controller, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarAdapter provideToolBarAdapter(
            final ViewController controller,
            final UIState uiState,
            final RootShortcutHandler shortcutHandler) {
        return new ToolBarAdapter(controller, uiState, shortcutHandler);
    }

    @Inject
    @Provides
    @Singleton
    public ShapeCanvasAdapter provideCanvasAdapter(
            final ViewController controller,
            final UIState uiState) {
        return new ShapeCanvasAdapter(controller, uiState);
    }

/*    @Inject
    @Provides
    @Singleton
    public LayersPanelAdapter provideLayersAdapter(
            final ViewController controller,
            final UIState uiState,
            final LayersPanel panel) {
        return new LayersPanelAdapter(controller, uiState, panel);
    }*/

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
    public LayersPanel provideLayersPanel(final LayersTree tree) {
        return new LayersPanel(tree);
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

    @Inject
    @Provides
    @Singleton
    public CanvasBuilder provideCanvasBuilder(final ShapeCanvasView canvasView) {
        return new CanvasBuilder(canvasView);
    }

    @Inject
    @Provides
    @Singleton
    public LayersPanelBuilder provideLayersPanelBuilder(final LayersPanel panel) {
        return new LayersPanelBuilder(panel);
    }

    @Inject
    @Provides
    @Singleton
    public LayersTreeBuilder provideLayersTreeBuilder(final LayersTree layersTree) {
        return new LayersTreeBuilder(layersTree);
    }
}
