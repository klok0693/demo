package org.example.astero_demo.functional.step;

import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.TestComponentHolder;

public abstract class Step {
    protected final Robot robot;

    protected Step(Robot robot) {
        this.robot = robot;
    }
}
