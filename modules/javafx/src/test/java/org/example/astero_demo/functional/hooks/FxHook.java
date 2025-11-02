package org.example.astero_demo.functional.hooks;

import io.cucumber.java.Before;
import org.example.astero_demo.functional.TestHelloApplication;
import org.testfx.api.FxToolkit;

public class FxHook {

    private static boolean started = false;

    private final TestHelloApplication application;

    public FxHook(final TestHelloApplication application) {
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
