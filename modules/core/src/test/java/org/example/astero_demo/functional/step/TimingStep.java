package org.example.astero_demo.functional.step;

import io.cucumber.java.en.When;
import org.example.astero_demo.functional.Robot;

public class TimingStep extends Step {

    public TimingStep(final Robot robot) {
        super(robot);
    }

    @When("hold")
    public void hold() {
        robot.hold();
    }
}
