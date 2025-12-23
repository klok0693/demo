package org.example.astero_demo.func.module.provider.hook;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.assertj.swing.core.Robot;
import org.example.astero_demo.func.SwingTestHelloApplication;
import org.example.astero_demo.func.hooks.SwingHook;

public class SwingHookProvider implements Provider<SwingHook> {
    private final SwingTestHelloApplication application;
    private final Robot robot;

    @Inject
    public SwingHookProvider(
            final SwingTestHelloApplication application,
            final Robot robot) {
        this.application = application;
        this.robot = robot;
    }

    @Override
    public SwingHook get() {
        return new SwingHook(application, robot);
    }
}
