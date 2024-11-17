package org.example.astero_demo.initialization.di.provider.ui.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.controller.ViewController;

public class RootAdapterProvider extends AdapterProvider<MutableUIState, RootAdapter> {
    private final RootShortcutHandler shortcutHandler;

    @Inject
    RootAdapterProvider(
            final ViewController controller,
            final MutableUIState uiState,
            final RootShortcutHandler shortcutHandler) {
        super(controller, uiState);
        this.shortcutHandler = shortcutHandler;
    }

    @Override
    protected RootAdapter createInstance() {
        return new RootAdapter(controller, uiState, shortcutHandler);
    }
}
