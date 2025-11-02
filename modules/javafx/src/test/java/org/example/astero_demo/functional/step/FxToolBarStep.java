package org.example.astero_demo.functional.step;

import io.cucumber.java.Before;
import io.cucumber.junit.platform.engine.Cucumber;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.functional.ApplicationFxRobot;
import org.example.astero_demo.functional.TestHelloApplication;
import org.example.astero_demo.fx.util.ColorUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
//@ExtendWith(ApplicationExtension.class)
public class FxToolBarStep /*extends ToolBarStep*/ {

    //@Before
    @Start
    public void start(final Stage stage) throws IOException {
        System.out.println("!!!!!!!!!!!!");
        final TestHelloApplication testApplication = new TestHelloApplication();
        testApplication.start(stage);

/*        this.componentHolder = testApplication;
        this.robot = new ApplicationFxRobot();*/
    }

    //@Override
    protected void testShape(final Shape shape) {
        assertEquals(ColorUtils.convert(Color.GREEN), Integer.parseInt(shape.getColor()));
    }
}
