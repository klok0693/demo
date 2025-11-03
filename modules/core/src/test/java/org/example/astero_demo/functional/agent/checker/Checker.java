package org.example.astero_demo.functional.agent.checker;

import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.functional.agent.StateAgent;

public abstract class Checker extends StateAgent {

    protected Checker(final TestComponentHolder componentHolder) {
        super(componentHolder);
    }
}
