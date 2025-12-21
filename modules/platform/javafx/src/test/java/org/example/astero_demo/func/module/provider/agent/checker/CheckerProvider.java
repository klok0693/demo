package org.example.astero_demo.func.module.provider.agent.checker;

import org.example.astero_demo.func.module.provider.agent.AgentProvider;
import org.example.astero_demo.functional.TestComponentHolder;

public abstract class CheckerProvider<T> extends AgentProvider<T> {

    protected CheckerProvider(final TestComponentHolder holder) {
        super(holder);
    }
}
