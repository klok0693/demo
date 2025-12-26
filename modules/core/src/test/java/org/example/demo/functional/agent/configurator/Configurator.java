package org.example.demo.functional.agent.configurator;

import org.example.demo.functional.TestComponentHolder;
import org.example.demo.functional.agent.StateAgent;

public abstract class Configurator extends StateAgent {

    protected Configurator(final TestComponentHolder componentHolder) {
        super(componentHolder);
    }
}
