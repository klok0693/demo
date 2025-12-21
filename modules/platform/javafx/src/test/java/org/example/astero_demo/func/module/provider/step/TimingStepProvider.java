package org.example.astero_demo.func.module.provider.step;

import com.google.inject.Inject;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.step.TimingStep;

public class TimingStepProvider extends StepProvider<TimingStep> {

    @Inject
    public TimingStepProvider(final Robot robot) {
        super(robot);
    }

    @Override
    public TimingStep get() {
        return new TimingStep(robot);
    }
}
