package org.example.astero_demo.initialization.di.provider.ui.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.LayersAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

public class LayersAdapterProvider extends LeafAdapterProvider<LayersAdapter> {
    private final StateHolder holder;

    @Inject
    LayersAdapterProvider(
            final ViewController controller,
            final UIState uiState,
            final StateHolder holder) {
        super(controller, uiState);
        this.holder = holder;
    }

    @Override
    protected LayersAdapter createInstance() {
        return new LayersAdapter(controller, uiState, holder);
    }
}
