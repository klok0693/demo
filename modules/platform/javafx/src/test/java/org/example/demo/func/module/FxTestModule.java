package org.example.demo.func.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.demo.func.FxApplicationRobot;
import org.example.demo.func.FxTestAppInitializer;
import org.example.demo.func.hooks.FxHook;
import org.example.demo.func.module.provider.TestComponentHolderProvider;
import org.example.demo.func.module.provider.hook.FxHookProvider;
import org.example.demo.functional.Robot;
import org.example.demo.functional.TestComponentHolder;
import org.example.demo.func.FxTestHelloApplication;

public class FxTestModule extends AbstractModule {

    @Override
    protected void configure()
    {
        bind(Robot.class).to(FxApplicationRobot.class);

        bind(TestComponentHolder.class).to(FxTestHelloApplication.class);
        bind(FxTestHelloApplication.class).toProvider(TestComponentHolderProvider.class);

        bind(FxHook.class).toProvider(FxHookProvider.class);
    }

    @Inject
    @Provides
    @Singleton
    public FxTestAppInitializer provideFxTestAppInitializer(final FxTestHelloApplication application) {
        return new FxTestAppInitializer(application);
    }
}
