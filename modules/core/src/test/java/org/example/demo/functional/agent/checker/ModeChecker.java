package org.example.demo.functional.agent.checker;

import io.cucumber.java.en.Then;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.state.mode.ModeSwitchable;
import org.example.demo.core.adapter.ui.state.mode.UIMode;
import org.example.demo.functional.TestComponentHolder;

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
        return getInstance(UIState.class).isActiveMode(mode);
    }
}
