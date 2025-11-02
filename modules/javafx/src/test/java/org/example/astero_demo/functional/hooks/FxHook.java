package org.example.astero_demo.functional.hooks;

import io.cucumber.java.Before;
import javafx.stage.Stage;
import org.example.astero_demo.functional.TestHelloApplication;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

public class FxHook /*extends ApplicationTest*/ {

    private static boolean started = false;

    @Before(order = 0)
    public void startFxApp() throws Exception {
        System.out.println("Set up !!!!!!!!!!!");
        if (!started) {
            FxToolkit.registerPrimaryStage();
            TestHelloApplication app =
                    (TestHelloApplication) FxToolkit.setupApplication(TestHelloApplication.class);
            started = true;
        }
    }

    //@Override
    public void start(Stage stage) throws Exception {
        // If TestFX needs custom startup
        System.out.println("Start !!!!!!!!!!!");
        new TestHelloApplication().start(stage);
    }
}
