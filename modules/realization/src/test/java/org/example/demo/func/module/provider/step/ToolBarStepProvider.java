package org.example.demo.func.module.provider.step;

import com.google.inject.Inject;
import org.example.demo.functional.Robot;
import org.example.demo.functional.step.ToolBarStep;

public class ToolBarStepProvider extends StepProvider<ToolBarStep> {

    @Inject
    public ToolBarStepProvider(final Robot robot) {
        super(robot);
    }

    @Override
    public ToolBarStep get() {
        return new ToolBarStep(robot);
    }
}
