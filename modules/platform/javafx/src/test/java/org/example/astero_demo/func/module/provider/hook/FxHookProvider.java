package org.example.astero_demo.func.module.provider.hook;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.example.astero_demo.func.FxTestHelloApplication;
import org.example.astero_demo.func.hooks.FxHook;

public class FxHookProvider implements Provider<FxHook> {
    private final FxTestHelloApplication application;

    @Inject
    public FxHookProvider(final FxTestHelloApplication application) {
        this.application = application;
    }

    @Override
    public FxHook get() {
        return new FxHook(application);
    }
}
