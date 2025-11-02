package org.example.astero_demo.functional.module.provider.checker;

import com.google.inject.Inject;
import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.functional.checker.ShapeChecker;

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
