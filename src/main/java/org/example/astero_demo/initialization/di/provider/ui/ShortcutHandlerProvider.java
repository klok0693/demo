package org.example.astero_demo.initialization.di.provider.ui;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.initialization.di.provider.InstanceProvider;

public class ShortcutHandlerProvider extends InstanceProvider<ShortcutHandlerProvider.ShortcutHandlerProxy> {
    private final ViewController controller;
    private final UIState uiState;
    private ParentAdapter parentAdapter;

    @Inject
    public ShortcutHandlerProvider(final ViewController controller, final UIState uiState) {
        this.controller = controller;
        this.uiState = uiState;
    }

    @Override
    protected ShortcutHandlerProxy createInstance() {
        final ShortcutHandlerProxy proxy = new ShortcutHandlerProxy(controller, uiState);
        if (parentAdapter != null) {
            proxy.setAdapter(parentAdapter);
        }
        return proxy;
    }

    public void setParentAdapter(final ParentAdapter adapter) {
        if (instance != null) {
            instance.setAdapter(adapter);
        }
        else {
            this.parentAdapter = adapter;
        }
    }

    static class ShortcutHandlerProxy extends RootShortcutHandler {

        public ShortcutHandlerProxy(LogicEventProcessor processor, UIState state) {
            super(processor, null, state);
        }

        void setAdapter(final ParentAdapter adapter) {
            this.parentAdapter = adapter;
        }
    }
}
