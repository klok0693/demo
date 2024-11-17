package org.example.astero_demo.initialization.di.provider.ui.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

public class CanvasAdapterProvider extends LeafAdapterProvider<CanvasAdapter> {
    private final StateHolder holder;

    @Inject
    CanvasAdapterProvider(
            final ViewController controller,
            final UIState uiState,
            final StateHolder holder) {
        super(controller, uiState);
        this.holder = holder;
    }

    @Override
    protected CanvasAdapter createInstance() {
        return new CanvasAdapter(controller, holder, uiState);
    }
}
