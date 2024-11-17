package org.example.astero_demo.initialization.di.provider.ui.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.ui.PropertyAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

public class PropertyAdapterProvider extends LeafAdapterProvider<PropertyAdapter> {

    @Inject
    PropertyAdapterProvider(final ViewController controller, final UIState uiState) {
        super(controller, uiState);
    }

    @Override
    protected PropertyAdapter createInstance() {
        return new PropertyAdapter(controller, uiState);
    }
}
