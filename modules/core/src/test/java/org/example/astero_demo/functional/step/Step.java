package org.example.astero_demo.functional.step;

import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.TestComponentHolder;

public abstract class Step {
    protected final Robot robot;
    protected final TestComponentHolder componentHolder;

    protected Step(Robot robot, TestComponentHolder holder) {
        this.robot = robot;
        this.componentHolder = holder;
    }
}
