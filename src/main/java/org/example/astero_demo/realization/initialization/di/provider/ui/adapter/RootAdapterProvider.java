package org.example.astero_demo.realization.initialization.di.provider.ui.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersPanelAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesPanelAdapter;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarPanelAdapter;
import org.example.astero_demo.port.ui.RootView;
import org.example.astero_demo.realization.initialization.di.provider.controller.ViewControllerProvider;

public class RootAdapterProvider extends AdapterProvider<MutableUIState, RootAdapter> {
    private final RootView rootView;
    private final CanvasAdapter canvasAdapter;
    private final LayersPanelAdapter layersAdapter;
    private final PropertiesPanelAdapter propertyAdapter;
    private final ToolBarAdapter toolBarAdapter;

    @Inject
    RootAdapterProvider(
            final ViewControllerProvider controller,
            final MutableUIState uiState,
            final RootView rootView,
            final CanvasAdapter canvasAdapter,
            final LayersPanelAdapter layersAdapter,
            final PropertiesPanelAdapter propertyAdapter,
            final ToolBarAdapter toolBarAdapter) {
        super(controller.get(), uiState);
        this.rootView = rootView;
        this.canvasAdapter = canvasAdapter;
        this.layersAdapter = layersAdapter;
        this.propertyAdapter = propertyAdapter;
        this.toolBarAdapter = toolBarAdapter;

        controller.setRootAdapter(get());
        //shortcutHandler.setParentAdapter(get());
    }

    @Override
    protected RootAdapter createInstance() {
        return new RootAdapter(
                controller,
                uiState,
                rootView,
                canvasAdapter,
                layersAdapter,
                propertyAdapter,
                toolBarAdapter);
    }
}
