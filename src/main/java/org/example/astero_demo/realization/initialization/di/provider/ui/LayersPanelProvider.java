package org.example.astero_demo.realization.initialization.di.provider.ui;

import com.google.inject.Inject;
import org.example.astero_demo.port.ui.LayersPanelView;
import org.example.astero_demo.port.ui.elements.LayersTree;
import org.example.astero_demo.realization.initialization.di.provider.InstanceProvider;
import org.example.astero_demo.realization.initialization.di.provider.ui.adapter.LayersPanelAdapterProvider;

public class LayersPanelProvider extends InstanceProvider<LayersPanelView> {

    @Inject
    public LayersPanelProvider(final LayersPanelAdapterProvider adapterProvider) {
        adapterProvider.setLayersPanel(get());
    }

    @Override
    protected LayersPanelView createInstance() {
        return new LayersPanelView();
    }
}
