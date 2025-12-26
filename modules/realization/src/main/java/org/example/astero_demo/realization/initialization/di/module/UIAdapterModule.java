package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.clipboard.Clipboard;
import org.example.astero_demo.core.adapter.clipboard.ClipboardAdapter;
import org.example.astero_demo.core.adapter.keyboard.EditorOperationAdapter;
import org.example.astero_demo.core.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.core.adapter.ui.CursorLocator;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersView;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.astero_demo.core.context.ops.OpsStateHolder;
import org.example.astero_demo.core.controller.keyboard.KeyboardController;
import org.example.astero_demo.core.logic.ShapeProcessor;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.adapter.ui.ParentAdapter;
import org.example.astero_demo.core.adapter.ui.RootAdapter;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.core.adapter.ui.canvas.ShapeCanvasAdapter;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersPanelAdapter;
import org.example.astero_demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.core.adapter.ui.property.PropertiesPanelAdapter;
import org.example.astero_demo.core.adapter.ui.property.PropertiesView;
import org.example.astero_demo.core.adapter.ui.state.MutableUIState;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.state.UIStateInstance;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarPanelAdapter;
import org.example.astero_demo.core.port.os.OSClipboard;
import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

/**
 * DI config for UI adapters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class UIAdapterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UIState.class).to(MutableUIState.class);
        bind(MutableUIState.class).to(UIStateInstance.class).in(Scopes.SINGLETON);

        bind(ParentAdapter.class).to(RootAdapter.class);
        //bind(ControllerAdapter.class).to(RootAdapter.class);

        bind(CanvasAdapter.class).to(ShapeCanvasAdapter.class).in(Scopes.SINGLETON);
        bind(LayersAdapter.class).to(LayersPanelAdapter.class).in(Scopes.SINGLETON);
        bind(PropertiesAdapter.class).to(PropertiesPanelAdapter.class).in(Scopes.SINGLETON);
        bind(ToolBarAdapter.class).to(ToolBarPanelAdapter.class).in(Scopes.SINGLETON);
        bind(OperationAdapter.class).to(EditorOperationAdapter.class).in(Scopes.SINGLETON);

        bind(CursorLocator.class).to(RootAdapter.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public UIStateInstance provideUIHolder(final ModelState modelState) {
        return new UIStateInstance(modelState);
    }

    @Inject
    @Provides
    @Singleton
    public EditorOperationAdapter provideKeyboardAdapter(
            final KeyboardController controller,
            final UIState state) {
        return new EditorOperationAdapter(controller, state);
    }

    @Inject
    @Provides
    @Singleton
    public ClipboardAdapter provideClipboardAdapter(
            final OSClipboard osClipboard,
            final Clipboard<Shape, ShapeParams> innerClipboard) {
        return new ClipboardAdapter(osClipboard, innerClipboard);
    }



    @Inject
    @Provides
    @Singleton
    public RootAdapter provideRootAdapter(
            final ModelState modelState,
            final ShapeProcessor controller,
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
            final ShapeProcessor controller,
            final UIState uiState,
            final LayersView layersRoot,
            final ParentAdapter parentAdapter) {
        return new LayersPanelAdapter(controller, uiState, layersRoot, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public PropertiesPanelAdapter providePropertyAdapter(
            final ShapeProcessor controller,
            final UIState uiState,
            final PropertiesView propertyView,
            final ParentAdapter parentAdapter) {
        return new PropertiesPanelAdapter(controller, uiState, propertyView, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public ToolBarPanelAdapter provideToolBarAdapter(
            final ShapeProcessor controller,
            final UIState uiState,
            final OperationAdapter keyBoardAdapter,
            final ToolBarView toolBarView,
            final ParentAdapter parentAdapter) {
        return new ToolBarPanelAdapter(controller, uiState, keyBoardAdapter, toolBarView, parentAdapter);
    }

    @Inject
    @Provides
    @Singleton
    public ShapeCanvasAdapter provideCanvasAdapter(
            final ShapeProcessor controller,
            final UIState uiState,
            final CanvasView canvasView,
            final ParentAdapter parentAdapter) {
        return new ShapeCanvasAdapter(controller, uiState, canvasView, parentAdapter);
    }
}
