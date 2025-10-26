package org.example.astero_demo.functional;

import javafx.scene.input.MouseButton;
import org.example.astero_demo.functional.Robot;
import org.testfx.api.FxRobot;

import java.util.concurrent.TimeUnit;

public class ApplicationFxRobot extends FxRobot implements Robot {

    @Override
    public void clickOnNode(final String id) {
        performAndHold(() ->super.clickOn(id));
    }

    @Override
    public void moveToNode(final String id) {
        performAndHold(() ->super.moveTo(id));
    }

    @Override
    public void dragAndDropBy(final double x, final double y) {
        performAndHold(() ->drag(MouseButton.PRIMARY).moveBy(x, y).drop());
    }

    @Override
    public void hold() {
        sleep(2, TimeUnit.SECONDS);
    }

    private void performAndHold(final Runnable runnable) {
        hold();
        runnable.run();
    }
}
