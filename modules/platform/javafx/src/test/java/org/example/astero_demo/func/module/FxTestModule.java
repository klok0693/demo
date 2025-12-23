package org.example.astero_demo.func.module;

import com.google.inject.AbstractModule;
import org.example.astero_demo.func.ApplicationFxRobot;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.func.TestHelloApplication;
import org.example.astero_demo.func.hooks.FxHook;
import org.example.astero_demo.func.module.provider.TestComponentHolderProvider;
import org.example.astero_demo.func.module.provider.hook.HookProvider;

public class FxTestModule extends AbstractModule {

    @Override
    protected void configure()
    {
        bind(Robot.class).to(ApplicationFxRobot.class);

        bind(TestComponentHolder.class).to(TestHelloApplication.class);
        bind(TestHelloApplication.class).toProvider(TestComponentHolderProvider.class);

        bind(FxHook.class).toProvider(HookProvider.class);
    }
}
