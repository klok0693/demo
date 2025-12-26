package org.example.astero_demo.func.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.lang3.StringUtils;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.example.astero_demo.func.SwingTestAppInitializer;
import org.example.astero_demo.func.SwingTestHelloApplication;

import java.util.regex.Pattern;

public class SwingHook {

    private static final Pattern COMPILE = Pattern.compile("\\s");
    private static boolean started = false;

    private final SwingTestHelloApplication application;

    private final Robot robot;
    private FrameFixture window;

    static {
        FailOnThreadViolationRepaintManager.install();
    }

    public SwingHook(final SwingTestHelloApplication application, final Robot robot) {
        this.application = application;
        this.robot = robot;
    }

    @Before(order = 0)
    public void setUp() {
        if (!started) {
            window = new FrameFixture(robot, GuiActionRunner.execute(() ->{
                final String properties = System.getProperty("app.properties", StringUtils.EMPTY);
                System.out.println(properties + "!!!!!!!!!!!!!");
                return new SwingTestAppInitializer(application).initialize(COMPILE.split(properties));
            }));
            window.show();
            started = true;
        }
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }
}
