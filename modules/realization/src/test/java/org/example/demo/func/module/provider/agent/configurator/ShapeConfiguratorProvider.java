package org.example.demo.func.module.provider.agent.configurator;

import com.google.inject.Inject;
import org.example.demo.functional.TestComponentHolder;
import org.example.demo.functional.agent.configurator.ShapeConfigurator;

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
