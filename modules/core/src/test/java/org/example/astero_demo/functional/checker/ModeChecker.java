package org.example.astero_demo.functional.checker;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitchable;
import org.example.astero_demo.core.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.functional.TestComponentHolder;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModeChecker extends Checker {

    public ModeChecker(final TestComponentHolder componentHolder) {
        super(componentHolder);
    }

    @Then("Application have shifted into insert mode")
    public void checkShiftedintoInsertMode() {
        assertTrue(isModeActive(UIMode.INSERT));
    }

    @Then("Application have shifted into single selection mode")
    public void checkShiftedintoDefaultMode() {
        assertTrue(isModeActive(UIMode.SINGLE_SELECTION));
    }

    private <T extends ModeSwitchable> boolean isModeActive(final UIMode<T> mode) {
        return componentHolder.getInstance(UIState.class).isActiveMode(mode);
    }
}
