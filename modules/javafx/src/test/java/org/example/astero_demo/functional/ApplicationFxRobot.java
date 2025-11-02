package org.example.astero_demo.functional;

import javafx.scene.input.MouseButton;
import org.example.astero_demo.functional.Robot;
import org.testfx.api.FxRobot;

import java.util.concurrent.TimeUnit;

public class ApplicationFxRobot extends FxRobot implements Robot {
    private static final String INSERT_BTN_ID = "#insertRectBtn";
    private static final String CANVAS_ID = "#canvasRoot";

    @Override
    public void clickOnCreateRectBtn() {
        clickOnNode(INSERT_BTN_ID);
    }

    @Override
    public void moveCursorOnCanvas() {
        moveToNode(CANVAS_ID);
    }

    @Override
    public void dragAndDrop(final double x, final double y) {
        performAndHold(() ->drag(MouseButton.PRIMARY).moveBy(x, y).drop());
    }

    @Override
    public void hold() {
        sleep(2, TimeUnit.SECONDS);
    }

    private void moveToNode(final String id) {
        performAndHold(() ->super.moveTo(id));
    }

    private void clickOnNode(final String id) {
        performAndHold(() ->super.clickOn(id));
    }

    private void performAndHold(final Runnable runnable) {
        hold();
        runnable.run();
    }
}
