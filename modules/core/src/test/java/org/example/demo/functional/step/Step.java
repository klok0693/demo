package org.example.demo.functional.step;

import org.example.demo.functional.Robot;

public abstract class Step {
    protected final Robot robot;

    protected Step(Robot robot) {
        this.robot = robot;
    }
}
