package org.example.demo.func.module.provider.agent.checker;

import com.google.inject.Inject;
import org.example.demo.functional.TestComponentHolder;
import org.example.demo.functional.agent.checker.ShapeChecker;

public class ShapeCheckerProvider extends CheckerProvider<ShapeChecker> {

    @Inject
    public ShapeCheckerProvider(final TestComponentHolder holder) {
        super(holder);
    }

    @Override
    public ShapeChecker get() {
        return new ShapeChecker(holder);
    }
}
