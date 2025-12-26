package org.example.demo.func.hooks;

import io.cucumber.java.Before;
import org.example.demo.func.FxTestAppInitializer;

public class FxHook {

    private static boolean started = false;

    private final FxTestAppInitializer initializer;

    public FxHook(final FxTestAppInitializer initializer) {
        this.initializer = initializer;
    }

    @Before(order = 0)
    public void startFxApp() throws Exception {
        if (!started) {
            initializer.initialize();
            started = true;
        }
    }
}
