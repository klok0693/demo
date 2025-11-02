package org.example.astero_demo.functional.module.provider.step;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.step.CanvasStep;

public class CanvasStepProvider extends StepProvider<CanvasStep> {

    @Inject
    public CanvasStepProvider(final Robot robot) {
        super(robot);
    }

    @Override
    public CanvasStep get() {
        return new CanvasStep(robot);
    }
}
