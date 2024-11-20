package org.example.astero_demo.realization.initialization.di.module.ui;

import com.google.inject.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.*;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.adapter.ui.canvas.ShapeCanvasAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersPanelAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesPanelAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesView;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.UIStateHolder;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarPanelAdapter;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.port.ui.LayersPanelView;
import org.example.astero_demo.port.ui.RootView;
import org.example.astero_demo.port.ui.ToolBarView;
import org.example.astero_demo.realization.initialization.di.provider.ui.*;

public class UIAdapterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UIState.class).to(MutableUIState.class);
        bind(MutableUIState.class).to(UIStateHolder.class).in(Scopes.SINGLETON);

        bind(EventHandler.class).to(RootShortcutHandler.class);
        //bind(RootShortcutHandler.class).toProvider(ShortcutHandlerProvider.class).asEagerSingleton();

        bind(ParentAdapter.class).to(RootAdapter.class);

        bind(PropertiesAdapter.class).to(PropertiesPanelAdapter.class).in(Scopes.SINGLETON);
        bind(ToolBarAdapter.class).to(ToolBarPanelAdapter.class);
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
    public RootShortcutHandler provideShortcutHandler(
            final ViewController processor,
            final ParentAdapter parentAdapter,
            final UIState state) {
        return new RootShortcutHandler(processor, parentAdapter, state);
    }

    @Inject
    @Provides
    @Singleton
    public RootAdapter provideRootAdapter(
            final ViewController controller,
            final MutableUIState uiState,
            final RootView rootView,
            final CanvasAdapter canvasAdapter,
            final LayersPanelAdapter layersAdapter,
            final PropertiesAdapter propertyAdapter,
            final ToolBarAdapter toolBarAdapter) {
        return new RootAdapter(controller, uiState, rootView, canvasAdapter, layersAdapter, propertyAdapter, toolBarAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public LayersPanelAdapter provideLayersAdapter(
            final ViewController controller,
            final UIState uiState,
            final LayersPanelView layersRoot,
            final ParentAdapter parentAdapter) {
        return new LayersPanelAdapter(controller, uiState, layersRoot, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public PropertiesPanelAdapter providePropertyAdapter(
            final ViewController controller,
            final UIState uiState,
            final PropertiesView propertyView,
            final ParentAdapter parentAdapter) {
        return new PropertiesPanelAdapter(controller, uiState, propertyView, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarPanelAdapter provideToolBarAdapter(
            final ViewController controller,
            final UIState uiState,
            final RootShortcutHandler shortcutHandler,
            final ToolBarView toolBarView,
            final ParentAdapter parentAdapter) {
        return new ToolBarPanelAdapter(controller, uiState, shortcutHandler, toolBarView, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public ShapeCanvasAdapter provideCanvasAdapter(
            final ViewController controller,
            final UIState uiState,
            final CanvasView canvasView,
            final ParentAdapter parentAdapter) {
        return new ShapeCanvasAdapter(controller, uiState, canvasView, parentAdapter);
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

/*    @Inject
    @Provides
    @Singleton
    public LayersPanelBuilder provideLayersPanelBuilder(final LayersPanelView panel) {
        return new LayersPanelBuilder(panel);
    }*/
}
