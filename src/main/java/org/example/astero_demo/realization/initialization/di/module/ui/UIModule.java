package org.example.astero_demo.realization.initialization.di.module.ui;

import com.google.inject.*;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.*;
import org.example.astero_demo.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.adapter.ui.canvas.ShapeCanvasAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersPanelAdapter;
import org.example.astero_demo.adapter.ui.property.PropertyPanelAdapter;
import org.example.astero_demo.adapter.ui.property.PropertyPanelUpdatable;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.UIStateHolder;
import org.example.astero_demo.adapter.ui.toolbar.OperationProcessor;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.port.ui.PropertyPanelView;
import org.example.astero_demo.port.ui.ToolBarView;
import org.example.astero_demo.realization.initialization.di.provider.ui.*;
import org.example.astero_demo.realization.initialization.di.provider.ui.adapter.*;

public class UIModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UIState.class).to(MutableUIState.class);
        bind(MutableUIState.class).to(UIStateHolder.class).in(Scopes.SINGLETON);

        bind(RootShortcutHandler.class).toProvider(ShortcutHandlerProvider.class).asEagerSingleton();
        bind(RootAdapter.class).toProvider(RootAdapterProvider.class).asEagerSingleton();

        //bind(LayersTree.class).toProvider(LayersTreeProvider.class).asEagerSingleton();
        bind(LayersPanelAdapter.class).toProvider(LayersPanelAdapterProvider.class).asEagerSingleton();

        bind(ParentAdapter.class).to(RootAdapter.class);

        bind(PropertyPanelUpdatable.class).to(PropertyPanelAdapter.class).in(Scopes.SINGLETON);
        bind(OperationProcessor.class).to(ToolBarAdapter.class);
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
            final UIState uiState,
            final PropertyPanelView propertyView) {
        return new PropertyPanelAdapter(controller, uiState, propertyView);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarAdapter provideToolBarAdapter(
            final ViewController controller,
            final UIState uiState,
            final RootShortcutHandler shortcutHandler,
            final ToolBarView toolBarView) {
        return new ToolBarAdapter(controller, uiState, shortcutHandler, toolBarView);
    }

    @Inject
    @Provides
    @Singleton
    public ShapeCanvasAdapter provideCanvasAdapter(
            final ViewController controller,
            final UIState uiState,
            final CanvasView canvasView) {
        return new ShapeCanvasAdapter(controller, uiState, canvasView);
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
