package org.example.demo.functional.step;

import io.cucumber.java.en.When;
import org.example.demo.functional.Robot;

public class TimingStep extends Step {

    public TimingStep(final Robot robot) {
        super(robot);
    }

    @When("Hold")
    public void hold() {
        robot.hold();
    }
}
