package org.example.demo.functional.agent.checker;

import org.example.demo.functional.TestComponentHolder;
import org.example.demo.functional.agent.StateAgent;

public abstract class Checker extends StateAgent {

    protected Checker(final TestComponentHolder componentHolder) {
        super(componentHolder);
    }
}
