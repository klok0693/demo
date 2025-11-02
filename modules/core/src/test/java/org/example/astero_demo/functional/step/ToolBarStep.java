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

public class ToolBarStep extends Step {
    //<editor-fold desc="Constants">
    //</editor-fold>

    public ToolBarStep(final Robot robot) {
        super(robot);
    }

    @When("Click on create Rectangular button")
    public void clickOnCreateRectBtn() {
        robot.clickOnCreateRectBtn();
    }
}
