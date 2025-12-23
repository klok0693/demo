package org.example.astero_demo.func.module.provider;

import com.google.inject.Provider;
import org.example.astero_demo.func.FxTestHelloApplication;

public class TestComponentHolderProvider implements Provider<FxTestHelloApplication> {
    private static FxTestHelloApplication holder;

    @Override
    public FxTestHelloApplication get() {
        if (holder == null) {
            holder = new FxTestHelloApplication();
        }
        return holder;
    }
}
