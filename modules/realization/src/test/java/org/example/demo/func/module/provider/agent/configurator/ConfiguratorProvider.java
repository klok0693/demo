package org.example.demo.func.module.provider.agent.configurator;

import org.example.demo.func.module.provider.agent.AgentProvider;
import org.example.demo.functional.TestComponentHolder;

public abstract class ConfiguratorProvider<T> extends AgentProvider<T> {

    protected ConfiguratorProvider(final TestComponentHolder holder) {
        super(holder);
    }
}
