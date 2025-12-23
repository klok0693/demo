package org.example.astero_demo.func.module;

import com.google.inject.AbstractModule;
import org.example.astero_demo.func.FxApplicationRobot;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.func.FxTestHelloApplication;
import org.example.astero_demo.func.hooks.FxHook;
import org.example.astero_demo.func.module.provider.TestComponentHolderProvider;
import org.example.astero_demo.func.module.provider.hook.FxHookProvider;

public class FxTestModule extends AbstractModule {

    @Override
    protected void configure()
    {
        bind(Robot.class).to(FxApplicationRobot.class);

        bind(TestComponentHolder.class).to(FxTestHelloApplication.class);
        bind(FxTestHelloApplication.class).toProvider(TestComponentHolderProvider.class);

        bind(FxHook.class).toProvider(FxHookProvider.class);
    }
}
