package org.example.astero_demo.functional.module.provider.checker;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.functional.agent.checker.ModeChecker;

public class ModeCheckerProvider extends CheckerProvider<ModeChecker> {
    private final Provider<TestComponentHolder> testComponentHolderProvider;

    @Inject
    public ModeCheckerProvider(final Provider<TestComponentHolder> testComponentHolderProvider) {
        super(null);
        this.testComponentHolderProvider = testComponentHolderProvider;
    }

    @Override
    public ModeChecker get() {
        return new ModeChecker(testComponentHolderProvider.get());
    }
}
