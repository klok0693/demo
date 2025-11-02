package org.example.astero_demo.functional.module.provider.hook;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.example.astero_demo.functional.TestHelloApplication;
import org.example.astero_demo.functional.hooks.FxHook;

public class HookProvider implements Provider<FxHook> {
    private final TestHelloApplication application;

    @Inject
    public HookProvider(final TestHelloApplication application) {
        this.application = application;
    }

    @Override
    public FxHook get() {
        return new FxHook(application);
    }
}
