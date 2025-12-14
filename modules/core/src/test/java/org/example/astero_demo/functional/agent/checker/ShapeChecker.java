package org.example.astero_demo.functional.agent.checker;

import io.cucumber.java.en.Then;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.functional.TestComponentHolder;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeChecker extends Checker {

    public ShapeChecker(final TestComponentHolder componentHolder) {
        super(componentHolder);
    }

    @Then("Shape was created with width: {double}, height: {double}")
    public void shapeWasCreated(final double width, final double height) {
        // Assert shape has been created successfully
        final ModelState modelState = getInstance(ModelState.class);
        final List<Shape> shapes = modelState.getShapes().toList();
        assertEquals(1, shapes.size());

        final Shape created = shapes.getFirst();
        assertEquals(width, Double.parseDouble(created.getWidth()));
        assertEquals(height, Double.parseDouble(created.getHeight()));
        assertEquals(2, Integer.parseInt(created.getPriority()));
        assertSame(ShapeType.RECT, created.getType());
    }

    @Then("Shape with id: {int} no longer exist")
    public void shapeNotExist(final int shapeId) {
        assertNull(getInstance(ModelState.class).getShape(shapeId));
    }
}
