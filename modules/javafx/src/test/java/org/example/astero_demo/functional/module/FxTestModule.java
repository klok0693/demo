package org.example.astero_demo.functional.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import io.cucumber.guice.CucumberScopes;
import org.example.astero_demo.functional.ApplicationFxRobot;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.functional.TestHelloApplication;
import org.example.astero_demo.functional.checker.ModeChecker;
import org.example.astero_demo.functional.checker.ShapeChecker;
import org.example.astero_demo.functional.hooks.FxHook;
import org.example.astero_demo.functional.module.provider.TestComponentHolderProvider;
import org.example.astero_demo.functional.module.provider.checker.ModeCheckerProvider;
import org.example.astero_demo.functional.module.provider.checker.ShapeCheckerProvider;
import org.example.astero_demo.functional.module.provider.hook.HookProvider;
import org.example.astero_demo.functional.module.provider.step.CanvasStepProvider;
import org.example.astero_demo.functional.module.provider.step.TimingStepProvider;
import org.example.astero_demo.functional.module.provider.step.ToolBarStepProvider;
import org.example.astero_demo.functional.step.CanvasStep;
import org.example.astero_demo.functional.step.TimingStep;
import org.example.astero_demo.functional.step.ToolBarStep;

public class FxTestModule extends AbstractModule {

    @Override
    protected void configure()
    {
        bind(Robot.class).to(ApplicationFxRobot.class);
/*        bind(TestComponentHolder.class).to(TestHelloApplication.class)
                .in(CucumberScopes.createScenarioScope()*//*Scopes.SINGLETON*//*);*/

        bind(TestComponentHolder.class).to(TestHelloApplication.class);
        bind(TestHelloApplication.class).toProvider(TestComponentHolderProvider.class);

        bind(ToolBarStep.class).toProvider(ToolBarStepProvider.class);
        bind(CanvasStep.class).toProvider(CanvasStepProvider.class);
        bind(TimingStep.class).toProvider(TimingStepProvider.class);

        bind(ShapeChecker.class).toProvider(ShapeCheckerProvider.class);
        bind(ModeChecker.class).toProvider(ModeCheckerProvider.class);

        bind(FxHook.class).toProvider(HookProvider.class);
    }
}
