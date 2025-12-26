package org.example.demo.functional.step;

import io.cucumber.java.en.When;
import org.example.demo.functional.Robot;

public class ToolBarStep extends Step {
    //<editor-fold desc="Constants">
    //</editor-fold>

    public ToolBarStep(final Robot robot) {
        super(robot);
    }

    @When("Click on create Rectangular button")
    public void clickOnCreateRectBtn() {
        robot.clickOnCreateRectBtn();
        robot.hold();
    }

    @When("Click on Delete button")
    public void clickOnDeleteBtn() {
        robot.clickOnDeleteRectBtn();
        robot.hold();
    }
}
