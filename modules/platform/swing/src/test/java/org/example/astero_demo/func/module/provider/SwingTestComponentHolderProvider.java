package org.example.astero_demo.func.module.provider;

import com.google.inject.Provider;
import org.example.astero_demo.func.SwingTestHelloApplication;

public class SwingTestComponentHolderProvider implements Provider<SwingTestHelloApplication> {

    private static final class HolderHolder {
        private static final SwingTestHelloApplication holder = new SwingTestHelloApplication();
    }

    @Override
    public SwingTestHelloApplication get() {
        return HolderHolder.holder;
    }
}
