package org.example.astero_demo.functional/*functional*/;

import io.cucumber.junit.platform.engine.Cucumber;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.functional.InsertShapeTest;
import org.example.astero_demo.fx.util.ColorUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test creation of the {@link Shape} via canvas drag&drop
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Cucumber
//@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "org.example.astero_demo.functional.hooks,org.example.astero_demo.functional.step")
/*(
        features = "classpath:features",
        glue = {
                "org.example.astero_demo.functional.steps",
                "org.example.astero_demo.functional.hooks"
        }
)*/
//@ExtendWith(ApplicationExtension.class)
public class FxInsertShapeTest /*extends ApplicationTest*//*InsertShapeTest*/ {

/*    @Override
    @Start
    public void start(final Stage stage) throws IOException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        final TestHelloApplication testApplication = new TestHelloApplication();
        testApplication.start(stage);

*//*        this.componentHolder = testApplication;
        this.robot = new ApplicationFxRobot();*//*
    }*/

    //@Override
/*    protected void testShape(final Shape shape) {
        assertEquals(ColorUtils.convert(Color.GREEN), Integer.parseInt(shape.getColor()));
    }*/
}