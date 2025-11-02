package org.example.astero_demo.functional.checker;

import io.cucumber.java.en.Then;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.entity.ShapeType;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.functional.TestComponentHolder;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ShapeChecker extends Checker {
    private static final double DIMENSION = 100.0;

    public ShapeChecker(final TestComponentHolder componentHolder) {
        super(componentHolder);
    }

    @Then("check results")
    public void check() {
        // Assert shape has been created successfully
        final ModelState modelState = componentHolder.getInstance(ModelState.class);
        final List<Shape> shapes = modelState.getShapes().toList();
        Assertions.assertEquals(1, shapes.size());

        final Shape created = shapes.getFirst();
        Assertions.assertEquals(DIMENSION, Double.parseDouble(created.getWidth()));
        Assertions.assertEquals(DIMENSION, Double.parseDouble(created.getHeight()));
        Assertions.assertEquals(2, Integer.parseInt(created.getPriority()));
        Assertions.assertSame(ShapeType.RECT, created.getType());
    }
}
