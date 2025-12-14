package org.example.astero_demo.func.module.provider.agent.configurator;

import com.google.inject.Inject;
import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.functional.agent.configurator.ShapeConfigurator;

public class ShapeConfiguratorProvider extends ConfiguratorProvider<ShapeConfigurator> {

    @Inject
    public ShapeConfiguratorProvider(final TestComponentHolder componentHolder) {
        super(componentHolder);
    }

    @Override
    public ShapeConfigurator get() {
        return new ShapeConfigurator(holder);
    }
}
