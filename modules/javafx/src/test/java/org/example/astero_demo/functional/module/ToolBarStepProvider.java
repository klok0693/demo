package org.example.astero_demo.functional.module;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.step.ToolBarStep;

public class ToolBarStepProvider implements Provider<ToolBarStep> {

    private final Robot robot;

    @Inject
    public ToolBarStepProvider(Robot robot) {
        this.robot = robot;
    }

    @Override
    public ToolBarStep get() {
        return new ToolBarStep(robot, null);
    }
}
