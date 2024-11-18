package org.example.astero_demo.initialization.di;

import com.google.inject.*;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.*;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.UIStateHolder;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.initialization.di.provider.ui.*;
import org.example.astero_demo.initialization.di.provider.ui.adapter.*;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.port.ui.canvas.tool.DragShapeTool;
import org.example.astero_demo.port.ui.canvas.tool.InsertTool;
import org.example.astero_demo.port.ui.canvas.tool.ShapeSelectionTool;
import org.example.astero_demo.port.ui.canvas.tool.ToolLayer;

public class UIModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UIState.class).to(MutableUIState.class);
        bind(MutableUIState.class).to(UIStateHolder.class).in(Scopes.SINGLETON);

        bind(RootShortcutHandler.class).toProvider(ShortcutHandlerProvider.class).asEagerSingleton();

        bind(CanvasAdapter.CanvasView.class).to(ShapeCanvasView.class);


        bind(ParentAdapter.class).to(RootAdapter.class);
        bind(RootAdapter.class).toProvider(RootAdapterProvider.class).asEagerSingleton();

        bind(BackgroundLayer.class).in(Scopes.SINGLETON);
        //bind(ShapeSelectionTool.class).in(Scopes.SINGLETON);
        //bind(DragShapeTool.class).in(Scopes.SINGLETON);
        //bind(InsertTool.class).in(Scopes.SINGLETON);
        //bind(ToolLayer.class).in(Scopes.SINGLETON);
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
    public PropertyAdapter providePropertyAdapter(
            final ViewController controller,
            final UIState uiState) {
        return new PropertyAdapter(controller, uiState);
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
    public CanvasAdapter provideCanvasAdapter(
            final ViewController controller,
            final UIState uiState) {
        return new CanvasAdapter(controller, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public LayersAdapter provideLayersAdapter(
            final ViewController controller,
            final UIState uiState,
            final ModelState holder) {
        return new LayersAdapter(controller, uiState, holder);
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
    public ShapeSelectionTool provideSelectionTool(final CanvasAdapter adapter, final ModelState modelState) {
        return new ShapeSelectionTool(adapter, modelState);
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
}
