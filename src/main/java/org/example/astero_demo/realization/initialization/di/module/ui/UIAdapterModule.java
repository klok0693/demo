package org.example.astero_demo.realization.initialization.di.module.ui;

import com.google.inject.*;
import org.example.astero_demo.adapter.keyboard.KeyBoardAdapter;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.*;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.adapter.ui.canvas.ShapeCanvasAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersPanelAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesPanelAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesView;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.UIStateHolder;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarPanelAdapter;
import org.example.astero_demo.controller.ui.ControllerAdapter;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.port.ui.LayersPanelView;
import org.example.astero_demo.port.ui.RootView;
import org.example.astero_demo.port.ui.ToolBarView;

public class UIAdapterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UIState.class).to(MutableUIState.class);
        bind(MutableUIState.class).to(UIStateHolder.class).in(Scopes.SINGLETON);

        bind(ParentAdapter.class).to(RootAdapter.class);
        bind(ControllerAdapter.class).to(RootAdapter.class);
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
    public KeyBoardAdapter provideKeyboardAdapte(
            final LogicEventProcessor processor,
            final UIState state,
            final ParentAdapter parentAdapter) {
        return new KeyBoardAdapter(processor, state, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public RootAdapter provideRootAdapter(
            final ModelState modelState,
            final LogicEventProcessor controller,
            final MutableUIState uiState,
            final RootView rootView,
            final CanvasAdapter canvasAdapter,
            final LayersAdapter layersAdapter,
            final PropertiesAdapter propertyAdapter,
            final ToolBarAdapter toolBarAdapter) {
        return new RootAdapter(
                modelState,
                controller,
                uiState,
                rootView,
                canvasAdapter,
                layersAdapter,
                propertyAdapter,
                toolBarAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public LayersPanelAdapter provideLayersAdapter(
            final LogicEventProcessor controller,
            final UIState uiState,
            final LayersPanelView layersRoot,
            final ParentAdapter parentAdapter) {
        return new LayersPanelAdapter(controller, uiState, layersRoot, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public PropertiesPanelAdapter providePropertyAdapter(
            final LogicEventProcessor controller,
            final UIState uiState,
            final PropertiesView propertyView,
            final ParentAdapter parentAdapter) {
        return new PropertiesPanelAdapter(controller, uiState, propertyView, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarPanelAdapter provideToolBarAdapter(
            final LogicEventProcessor controller,
            final UIState uiState,
            final KeyBoardAdapter keyBoardAdapter,
            final ToolBarView toolBarView,
            final ParentAdapter parentAdapter) {
        return new ToolBarPanelAdapter(controller, uiState, keyBoardAdapter, toolBarView, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public ShapeCanvasAdapter provideCanvasAdapter(
            final LogicEventProcessor controller,
            final UIState uiState,
            final CanvasView canvasView,
            final ParentAdapter parentAdapter) {
        return new ShapeCanvasAdapter(controller, uiState, canvasView, parentAdapter);
    }
}
