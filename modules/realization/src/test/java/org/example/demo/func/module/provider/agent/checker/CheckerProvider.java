package org.example.demo.func.module.provider.agent.checker;

import org.example.demo.func.module.provider.agent.AgentProvider;
import org.example.demo.functional.TestComponentHolder;

public abstract class CheckerProvider<T> extends AgentProvider<T> {

    protected CheckerProvider(final TestComponentHolder holder) {
        super(holder);
    }
}
