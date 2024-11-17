package org.example.astero_demo.initialization.di.provider.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.initialization.di.provider.InstanceProvider;

public class ShortcutProvider extends InstanceProvider<RootShortcutHandler> {
    private final ViewController controller;
    private final UIState uiState;

    @Inject
    public ShortcutProvider(final ViewController controller, final UIState uiState) {
        this.controller = controller;
        this.uiState = uiState;
    }

    @Override
    protected RootShortcutHandler createInstance() {
        return new RootShortcutHandler(controller, uiState);
    }
}
