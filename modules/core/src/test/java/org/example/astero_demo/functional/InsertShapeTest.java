package org.example.astero_demo.functional;

import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.entity.ShapeType;
import org.example.astero_demo.core.model.state.ModelState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test creation of the {@link Shape} via canvas drag&drop
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class InsertShapeTest {
    //<editor-fold desc="Constants">
    private static final String INSERT_BTN_ID = "#insertRectBtn";
    private static final String CANVAS_ID = "#canvasRoot";
    private static final double DIMENSION = 100.0;
    //</editor-fold>

    protected TestComponentHolder componentHolder;
    protected Robot robot;

    @Test
    @DisplayName("New shape inserted using canvas drag&drop")
    void testInsertNewShape() {
        // Click on "Rect" button
        robot.clickOnNode(INSERT_BTN_ID);
        // Move cursor onto canvas
        robot.moveToNode(CANVAS_ID);
        // Drag required space and drop
        robot.dragAndDropBy(DIMENSION, DIMENSION);
        robot.hold();

        // Assert shape has been created successfully
        final ModelState modelState = componentHolder.getInstance(ModelState.class);
        final List<Shape> shapes = modelState.getShapes().toList();
        Assertions.assertEquals(1, shapes.size());

        final Shape created = shapes.getFirst();
        Assertions.assertEquals(DIMENSION, Double.parseDouble(created.getWidth()));
        Assertions.assertEquals(DIMENSION, Double.parseDouble(created.getHeight()));
        Assertions.assertEquals(2, Integer.parseInt(created.getPriority()));
        Assertions.assertSame(ShapeType.RECT, created.getType());

        testShape(created);
    }

    protected abstract void testShape(Shape shape);
}
