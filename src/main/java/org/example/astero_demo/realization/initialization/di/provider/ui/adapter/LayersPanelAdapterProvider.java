package org.example.astero_demo.realization.initialization.di.provider.ui.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.ui.layerspanel.LayersPanelAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.port.ui.LayersPanel;

public class LayersPanelAdapterProvider extends AdapterProvider<UIState, LayersPanelAdapterProvider.LayersAdapterProxy> {

    @Inject
    LayersPanelAdapterProvider(final ViewController controller, final UIState uiState) {
        super(controller, uiState);
    }

    public void setLayersPanel(final LayersPanel panel) {
        this.instance.setLayersPanel(panel);
    }

    @Override
    protected LayersAdapterProxy createInstance() {
        return new LayersAdapterProxy(controller, uiState);
    }

    static class LayersAdapterProxy extends LayersPanelAdapter {

        public LayersAdapterProxy(ViewController controller, UIState uiState) {
            super(controller, uiState, null);
        }

        public void setLayersPanel(final LayersPanel panel) {
            this.layersRoot = panel;
        }
    }
}
