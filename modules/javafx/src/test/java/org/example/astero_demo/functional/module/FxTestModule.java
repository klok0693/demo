package org.example.astero_demo.functional.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import org.example.astero_demo.functional.ApplicationFxRobot;
import org.example.astero_demo.functional.Robot;
import org.example.astero_demo.functional.hooks.FxHook;
import org.example.astero_demo.functional.step.ToolBarStep;

public class FxTestModule extends AbstractModule {

    @Override
    protected void configure()
    {
        bind(Robot.class).to(ApplicationFxRobot.class);
        bind(ToolBarStep.class).toProvider(ToolBarStepProvider.class);
        //bind(FxHook.class);
    }

/*    @Provides
    public Robot provideRobot() {
        return new ApplicationFxRobot();
    }*/

/*    @Inject
    @Provides
    public ToolBarStep createToolBarStep(final Robot robot) {
        return new ToolBarStep(robot, null);
    }*/
}
