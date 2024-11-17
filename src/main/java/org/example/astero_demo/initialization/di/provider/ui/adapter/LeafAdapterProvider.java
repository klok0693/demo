package org.example.astero_demo.initialization.di.provider.ui.adapter;

import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

abstract class LeafAdapterProvider<T extends LeafAdapter> extends AdapterProvider<UIState, T> {

    LeafAdapterProvider(final ViewController controller, final UIState uiState) {
        super(controller, uiState);
    }
}
