package org.example.demo.functional.agent;

import org.example.demo.functional.TestComponentHolder;

public abstract class StateAgent {
    private final TestComponentHolder componentHolder;

    protected StateAgent(final TestComponentHolder componentHolder) {
        this.componentHolder = componentHolder;
    }

    protected <T> T getInstance(final Class<T> tClass) {
        return componentHolder.getInstance(tClass);
    };
}
