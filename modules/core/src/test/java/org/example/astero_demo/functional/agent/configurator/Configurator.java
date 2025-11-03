package org.example.astero_demo.functional.agent.configurator;

import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.functional.agent.StateAgent;

public abstract class Configurator extends StateAgent {

    protected Configurator(final TestComponentHolder componentHolder) {
        super(componentHolder);
    }
}
