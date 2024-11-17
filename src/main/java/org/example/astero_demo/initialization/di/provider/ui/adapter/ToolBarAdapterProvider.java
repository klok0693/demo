package org.example.astero_demo.initialization.di.provider.ui.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.ui.ToolBarAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

public class ToolBarAdapterProvider extends LeafAdapterProvider<ToolBarAdapter> {
    private final RootShortcutHandler shortcutHandler;

    @Inject
    ToolBarAdapterProvider(
            final ViewController controller,
            final UIState uiState,
            final RootShortcutHandler shortcutHandler) {
        super(controller, uiState);
        this.shortcutHandler = shortcutHandler;
    }

    @Override
    protected ToolBarAdapter createInstance() {
        return new ToolBarAdapter(controller, uiState, shortcutHandler);
    }
}
