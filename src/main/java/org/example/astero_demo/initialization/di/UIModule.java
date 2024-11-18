package org.example.astero_demo.initialization.di;

import com.google.inject.*;
import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.*;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.UIStateHolder;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.initialization.di.provider.ui.*;
import org.example.astero_demo.initialization.di.provider.ui.adapter.*;
import org.example.astero_demo.port.ui.canvas.CanvasView;
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

        bind(CanvasView.CanvasDelegate.class).to(CanvasAdapter.class);


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
    public UIStateHolder provideUIHolder(final StateHolder holder) {
        return new UIStateHolder(holder);
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
            final StateHolder holder) {
        return new LayersAdapter(controller, uiState, holder);
    }

    @Inject
    @Provides
    @Singleton
    public DragShapeTool provideDragTool(
            final CanvasView.CanvasDelegate delegate,
            final StateHolder stateHolder,
            final UIState uiState) {
        return new DragShapeTool(delegate, stateHolder, uiState);
    }

    @Inject
    @Provides
    @Singleton
    public ShapeSelectionTool provideSelectionTool(final CanvasView.CanvasDelegate delegate, final StateHolder holder) {
        return new ShapeSelectionTool(delegate, holder);
    }

    @Inject
    @Provides
    @Singleton
    public InsertTool provideInsertTool(final CanvasView.CanvasDelegate delegate, final UIState uiState) {
        return new InsertTool(delegate, uiState);
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
    public CanvasView provideCanvasView(
            final UIState state,
            final StateHolder holder,
            final CanvasView.CanvasDelegate delegate,
            final BackgroundLayer backgroundLayer,
            final ToolLayer toolLayer) {
        return new CanvasView(state, holder, delegate, backgroundLayer, toolLayer);
    }
}
