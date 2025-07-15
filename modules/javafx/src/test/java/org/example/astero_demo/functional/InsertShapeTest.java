package org.example.astero_demo.functional;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.entity.ShapeType;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.fx.util.ColorUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Test creation of the {@link Shape} via canvas drag&drop
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class InsertShapeTest extends ApplicationTest {
    //<editor-fold desc="Constants">
    private static final String INSERT_BTN_ID = "#insertRectBtn";
    private static final String CANVAS_ID = "#canvasRoot";
    private static final double DIMENSION = 100.0;
    //</editor-fold>

    private TestHelloApplication testApplication;

    @Override
    public void start(final Stage stage) throws Exception {
        this.testApplication = new TestHelloApplication();
        testApplication.start(stage);
    }

    @Test
    @DisplayName("New shape inserted using canvas drag&drop")
    void testInsertNewShape() {
        // Click on "Rect" button
        performAndHold(() -> clickOn(INSERT_BTN_ID));
        // Move cursor onto canvas
        performAndHold(() -> moveTo(CANVAS_ID));
        // Drag required space and drop
        performAndHold(() -> drag(MouseButton.PRIMARY).moveBy(DIMENSION, DIMENSION).drop());
        hold();

        // Assert shape has been created successfully
        final ModelState modelState = testApplication.getInstance(ModelState.class);
        final List<Shape> shapes = modelState.getShapes().toList();
        Assertions.assertEquals(1, shapes.size());

        final Shape created = shapes.getFirst();
        Assertions.assertEquals(DIMENSION, Double.parseDouble(created.getWidth()));
        Assertions.assertEquals(DIMENSION, Double.parseDouble(created.getHeight()));
        Assertions.assertEquals(2, Integer.parseInt(created.getPriority()));
        assertEquals(ColorUtils.convert(Color.GREEN), Integer.parseInt(created.getColor()));
        Assertions.assertSame(ShapeType.RECT, created.getType());
    }

    private void performAndHold(final Runnable runnable) {
        hold();
        runnable.run();
    }

    private void hold() {
        sleep(2, TimeUnit.SECONDS);
    }
}
