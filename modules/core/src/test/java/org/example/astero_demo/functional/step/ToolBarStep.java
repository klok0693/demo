package org.example.astero_demo.functional.step;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.entity.ShapeType;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.TestComponentHolder;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public /*abstract*/ class ToolBarStep extends Step {
    //<editor-fold desc="Constants">
    private static final String INSERT_BTN_ID = "#insertRectBtn";
    private static final String CANVAS_ID = "#canvasRoot";
    private static final double DIMENSION = 100.0;

    public ToolBarStep(Robot robot, TestComponentHolder holder) {
        super(robot, holder);
    }
    //</editor-fold>

    @When("click on create Rectangular button")
    public void clickOnCreateRectBtn() {
        robot.clickOnNode(INSERT_BTN_ID);
    }

    @When("cursor are moved on a Canvas")
    public void moveCursorOnCanvas() {
        robot.moveToNode(CANVAS_ID);
    }

    @When("drag and drop")
    public void dragAndDrop() {
        robot.dragAndDropBy(DIMENSION, DIMENSION);
    }

    @When("hold")
    public void hold() {
        robot.hold();
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

        //testShape(created);
    }

    //protected abstract void testShape(Shape shape);
}
