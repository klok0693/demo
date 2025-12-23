package org.example.astero_demo.func.hooks;

import io.cucumber.java.Before;
import org.example.astero_demo.func.FxTestHelloApplication;
import org.testfx.api.FxToolkit;

public class FxHook {

    private static boolean started = false;

    private final FxTestHelloApplication application;

    public FxHook(final FxTestHelloApplication application) {
        this.application = application;
    }

    @Before(order = 0)
    public void startFxApp() throws Exception {
        if (!started) {
            FxToolkit.registerPrimaryStage();
            FxToolkit.setupApplication(() -> application);
            started = true;
        }
    }
}
