package org.example.astero_demo.func.module;

import com.google.inject.*;
import org.assertj.swing.core.BasicRobot;
import org.example.astero_demo.func.SwingApplicationRobot;
import org.example.astero_demo.func.SwingTestHelloApplication;
import org.example.astero_demo.func.hooks.CacioInitializer;
import org.example.astero_demo.func.hooks.SwingHook;
import org.example.astero_demo.func.launchers.SwingDeleteShapeTest;
import org.example.astero_demo.func.launchers.SwingInsertShapeTest;
import org.example.astero_demo.func.module.provider.SwingTestComponentHolderProvider;
import org.example.astero_demo.func.module.provider.hook.SwingHookProvider;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.TestComponentHolder;

public class SwingTestModule extends AbstractModule {

    @Override
    protected void configure()
    {
        bind(CacioInitializer.class).toInstance(new CacioInitializer());

        bind(Robot.class).to(SwingApplicationRobot.class);

        bind(TestComponentHolder.class).to(SwingTestHelloApplication.class);
        bind(SwingTestHelloApplication.class).toProvider(SwingTestComponentHolderProvider.class);

        bind(SwingHook.class).toProvider(SwingHookProvider.class).in(Scopes.SINGLETON);

        bind(SwingDeleteShapeTest.class).in(Scopes.SINGLETON);
        bind(SwingInsertShapeTest.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public org.assertj.swing.core.Robot provideAssertJRobot() {
        return BasicRobot.robotWithNewAwtHierarchy();
    }

    @Inject
    @Provides
    @Singleton
    public SwingApplicationRobot provideSwingRobotHolder(org.assertj.swing.core.Robot robot) {
        return new SwingApplicationRobot(robot);
    }
}
