package org.example.demo.functional.step;

import io.cucumber.java.en.When;
import org.example.demo.core.context.state.ModelStateHolder;
import org.example.demo.functional.Robot;
import org.example.demo.functional.TestComponentHolder;
import org.example.demo.model.entity.Shape;

public class CanvasStep extends Step {
    private final TestComponentHolder holder;

    public CanvasStep(final Robot robot, final TestComponentHolder holder) {
        super(robot);
        this.holder = holder;
    }

    @When("Cursor are moved on a Canvas")
    public void moveCursorOnCanvas() {
        robot.moveCursorOnCanvas();
        robot.hold();
    }

    @When("Drag and drop by x: {double}, y: {double}")
    public void dragAndDrop(double x, double y) {
        robot.dragAndDrop(x, y);
        robot.hold();
    }

    @When("Shape with id: {int} are selected on canvas")
    public void selectShapeOnCanvas(final int shapeId) {
        moveCursorOnCanvas();

        final Shape shape = holder.getInstance(ModelStateHolder.class).getShape(shapeId);
        final double x = Double.parseDouble(shape.getX());
        final double y = Double.parseDouble(shape.getY());
        final double width = Double.parseDouble(shape.getWidth());
        final double height = Double.parseDouble(shape.getHeight());

        robot.moveCursorOnCanvasBy(x + (width / 2), y + (height / 2));
        robot.mousePrimaryClick();
        robot.hold();
    }
}
