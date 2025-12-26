package org.example.astero_demo.func;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import org.example.astero_demo.core.port.ui.markup.ElementID;
import org.example.astero_demo.functional.Robot;
import org.testfx.api.FxRobot;

import java.util.concurrent.TimeUnit;

import static org.example.astero_demo.core.port.ui.markup.ElementID.*;

public class FxApplicationRobot extends FxRobot implements Robot {

    @Override
    public void clickOnCreateRectBtn() {
        clickOnNode(INSERT_RECT_BTN);
    }

    @Override
    public void clickOnDeleteRectBtn() {
        clickOnNode(DELETE_BTN);
    }

    @Override
    public void moveCursorOnCanvasBy(final double x, final double y) {
        final Region node = lookup(SELECTOR + CANVAS_ROOT).query();
        final double offsetX = -(node.getWidth() / 2) + x;
        final double offsetY = -(node.getHeight() / 2) + y;
        moveBy(offsetX, offsetY);
    }

    @Override
    public void moveCursorOnCanvas() {
        moveToNode(CANVAS_ROOT);
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

    private void moveToNode(final Enum<ElementID> id) {
        performAndHold(() ->super.moveTo(SELECTOR + id));
    }

    private static final String SELECTOR = "#";

    private void clickOnNode(final Enum<ElementID> id) {
        performAndHold(() -> super.clickOn(SELECTOR + id));
    }

    private void performAndHold(final Runnable runnable) {
        hold();
        runnable.run();
    }
}
