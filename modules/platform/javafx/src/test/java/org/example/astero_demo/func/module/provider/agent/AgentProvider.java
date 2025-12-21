package org.example.astero_demo.func.module.provider.agent;

import com.google.inject.Provider;
import org.example.astero_demo.functional.TestComponentHolder;

public abstract class AgentProvider<T> implements Provider<T> {
    protected final TestComponentHolder holder;

    protected AgentProvider(final TestComponentHolder holder) {
        this.holder = holder;
    }
}
