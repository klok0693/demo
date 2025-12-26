package org.example.demo.func.module.provider.step;

import com.google.inject.Provider;
import org.example.demo.functional.Robot;

public abstract class StepProvider<T> implements Provider<T> {
    protected final Robot robot;

    protected StepProvider(final Robot robot) {
        this.robot = robot;
    }
}
