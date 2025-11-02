package org.example.astero_demo.functional.checker;

import org.example.astero_demo.functional.TestComponentHolder;

public abstract class Checker {
    protected final TestComponentHolder componentHolder;

    protected Checker(final TestComponentHolder componentHolder) {
        this.componentHolder = componentHolder;
    }
}
