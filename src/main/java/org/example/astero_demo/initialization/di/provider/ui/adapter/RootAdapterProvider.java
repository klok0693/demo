package org.example.astero_demo.initialization.di.provider.ui.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.initialization.di.provider.controller.ViewControllerProvider;
import org.example.astero_demo.initialization.di.provider.ui.ShortcutHandlerProvider;

public class RootAdapterProvider extends AdapterProvider<MutableUIState, RootAdapter> {
    private final ShortcutHandlerProvider shortcutHandler;

    @Inject
    RootAdapterProvider(
            final ViewControllerProvider controller,
            final MutableUIState uiState,
            final ShortcutHandlerProvider shortcutHandler) {
        super(controller.get(), uiState);
        this.shortcutHandler = shortcutHandler;

        controller.setRootAdapter(get());
        shortcutHandler.setParentAdapter(get());
    }

    @Override
    protected RootAdapter createInstance() {
        return new RootAdapter(controller, uiState, shortcutHandler.get());
    }
}
