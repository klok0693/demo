package org.example.astero_demo.func.module.provider.agent.configurator;

import com.google.inject.Provider;
import org.example.astero_demo.func.module.provider.agent.AgentProvider;
import org.example.astero_demo.functional.TestComponentHolder;

public abstract class ConfiguratorProvider<T> extends AgentProvider<T> {

    protected ConfiguratorProvider(final TestComponentHolder holder) {
        super(holder);
    }
}
