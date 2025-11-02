package org.example.astero_demo.functional.step;

import io.cucumber.java.en.When;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.TestComponentHolder;

public class CanvasStep extends Step {
    private static final double DIMENSION = 100.0;

    public CanvasStep(final Robot robot) {
        super(robot);
    }

    @When("Cursor are moved on a Canvas")
    public void moveCursorOnCanvas() {
        robot.moveCursorOnCanvas();
    }

    @When("Drag and drop by x: {double}, y: {double}")
    public void dragAndDrop(double x, double y) {
        robot.dragAndDrop(x, y);
        robot.hold();
    }
}
