package org.example.demo.func.module.provider.step;

import com.google.inject.Inject;
import org.example.demo.functional.Robot;
import org.example.demo.functional.TestComponentHolder;
import org.example.demo.functional.step.CanvasStep;

public class CanvasStepProvider extends StepProvider<CanvasStep> {
    private final TestComponentHolder holder;

    @Inject
    public CanvasStepProvider(final Robot robot, final TestComponentHolder holder) {
        super(robot);
        this.holder = holder;
    }

    @Override
    public CanvasStep get() {
        return new CanvasStep(robot, holder);
    }
}
