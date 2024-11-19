package org.example.astero_demo.realization.initialization.di.provider.ui.adapter;

import org.example.astero_demo.adapter.ui.UIAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.realization.initialization.di.provider.InstanceProvider;

abstract class AdapterProvider<U extends UIState,T extends UIAdapter<U>> extends InstanceProvider<T> {
    protected final ViewController controller;
    protected final U uiState;

    AdapterProvider(final ViewController controller, final U uiState) {
        this.controller = controller;
        this.uiState = uiState;
    }
}
