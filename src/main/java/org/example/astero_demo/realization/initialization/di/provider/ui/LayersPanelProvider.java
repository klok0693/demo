package org.example.astero_demo.realization.initialization.di.provider.ui;

import com.google.inject.Inject;
import org.example.astero_demo.port.ui.LayersPanel;
import org.example.astero_demo.port.ui.elements.LayersTree;
import org.example.astero_demo.realization.initialization.di.provider.InstanceProvider;
import org.example.astero_demo.realization.initialization.di.provider.ui.adapter.LayersPanelAdapterProvider;

public class LayersPanelProvider extends InstanceProvider<LayersPanel> {
    private final LayersTree tree;

    @Inject
    public LayersPanelProvider(final LayersTree tree, final LayersPanelAdapterProvider adapterProvider) {
        this.tree = tree;
        adapterProvider.setLayersPanel(get());
    }

    @Override
    protected LayersPanel createInstance() {
        return new LayersPanel(tree);
    }
}
