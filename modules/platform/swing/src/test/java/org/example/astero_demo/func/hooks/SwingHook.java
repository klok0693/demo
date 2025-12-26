package org.example.astero_demo.func.hooks;

import com.formdev.flatlaf.FlatLightLaf;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.example.astero_demo.func.SwingTestAppInitializer;
import org.example.astero_demo.func.SwingTestHelloApplication;

public class SwingHook {

    private static boolean started = false;

    private final SwingTestHelloApplication application;

    private final Robot robot;
    private FrameFixture window;

    static {
        FailOnThreadViolationRepaintManager.install();
        //FlatLightLaf.setup();
    }

    public SwingHook(final SwingTestHelloApplication application, final Robot robot) {
        this.application = application;
        this.robot = robot;
    }

    @Before(order = 0)
    public void setUp() {
        if (!started) {
            window = new FrameFixture(robot, GuiActionRunner.execute(() ->
                    new SwingTestAppInitializer(application).initialize()));
            window.show();
            started = true;
        }
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }
}
