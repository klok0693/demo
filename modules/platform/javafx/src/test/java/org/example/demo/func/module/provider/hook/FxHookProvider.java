package org.example.demo.func.module.provider.hook;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.example.demo.func.FxTestAppInitializer;
import org.example.demo.func.hooks.FxHook;

public class FxHookProvider implements Provider<FxHook> {
    private final FxTestAppInitializer initializer;

    @Inject
    public FxHookProvider(final FxTestAppInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public FxHook get() {
        return new FxHook(initializer);
    }
}
