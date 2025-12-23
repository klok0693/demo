package org.example.astero_demo.func;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import org.example.astero_demo.functional.Robot;
import org.testfx.api.FxRobot;

import java.util.concurrent.TimeUnit;

public class FxApplicationRobot extends FxRobot implements Robot {
    private static final String INSERT_BTN_ID = "#insertRectBtn";
    private static final String DELETE_BTN_ID = "#deleteBtn";
    private static final String CANVAS_ID = "#canvasRoot";

    @Override
    public void clickOnCreateRectBtn() {
        clickOnNode(INSERT_BTN_ID);
    }

    @Override
    public void clickOnDeleteRectBtn() {
        clickOnNode(DELETE_BTN_ID);
    }

    @Override
    public void moveCursorOnCanvasBy(final double x, final double y) {
        final Region node = lookup(CANVAS_ID).query();
        final double offsetX = -(node.getWidth() / 2) + x;
        final double offsetY = -(node.getHeight() / 2) + y;
        moveBy(offsetX, offsetY);
    }

    @Override
    public void moveCursorOnCanvas() {
        moveToNode(CANVAS_ID);
    }

    @Override
    public void mousePrimaryClick() {
        clickOn(MouseButton.PRIMARY);
    }

    @Override
    public void dragAndDrop(final double x, final double y) {
        performAndHold(() -> drag(MouseButton.PRIMARY).moveBy(x, y).drop());
    }

    @Override
    public void hold() {
        sleep(2, TimeUnit.SECONDS);
    }

    private void moveToNode(final String id) {
        performAndHold(() ->super.moveTo(id));
    }

    private void clickOnNode(final String id) {
        performAndHold(() -> super.clickOn(id));
    }

    private void performAndHold(final Runnable runnable) {
        hold();
        runnable.run();
    }
}
