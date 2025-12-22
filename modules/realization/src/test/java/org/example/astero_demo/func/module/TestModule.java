package org.example.astero_demo.func.module;

import com.google.inject.AbstractModule;
import org.example.astero_demo.func.module.provider.agent.configurator.ShapeConfiguratorProvider;
import org.example.astero_demo.functional.agent.checker.ModeChecker;
import org.example.astero_demo.functional.agent.checker.ShapeChecker;
import org.example.astero_demo.func.module.provider.agent.checker.ModeCheckerProvider;
import org.example.astero_demo.func.module.provider.agent.checker.ShapeCheckerProvider;
import org.example.astero_demo.func.module.provider.step.CanvasStepProvider;
import org.example.astero_demo.func.module.provider.step.TimingStepProvider;
import org.example.astero_demo.func.module.provider.step.ToolBarStepProvider;
import org.example.astero_demo.functional.agent.configurator.ShapeConfigurator;
import org.example.astero_demo.functional.step.CanvasStep;
import org.example.astero_demo.functional.step.TimingStep;
import org.example.astero_demo.functional.step.ToolBarStep;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ToolBarStep.class).toProvider(ToolBarStepProvider.class);
        bind(CanvasStep.class).toProvider(CanvasStepProvider.class);
        bind(TimingStep.class).toProvider(TimingStepProvider.class);

        bind(ShapeChecker.class).toProvider(ShapeCheckerProvider.class);
        bind(ModeChecker.class).toProvider(ModeCheckerProvider.class);

        bind(ShapeConfigurator.class).toProvider(ShapeConfiguratorProvider.class);
    }
}
