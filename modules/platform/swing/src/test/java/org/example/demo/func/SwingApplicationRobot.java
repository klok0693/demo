package org.example.demo.func;

import org.assertj.swing.core.MouseButton;
import org.assertj.swing.timing.Pause;
import org.example.demo.core.port.ui.markup.ElementID;
import org.example.demo.functional.Robot;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.example.demo.core.port.ui.markup.ElementID.*;

public class SwingApplicationRobot implements Robot {

    private final org.assertj.swing.core.Robot robot;

    public SwingApplicationRobot(final org.assertj.swing.core.Robot robot) {
        this.robot = robot;
    }

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
        Component canvas = robot.finder().findByName(CANVAS_ROOT.toString());

        Rectangle bounds = canvas.getBounds();
        Point screenLocation = canvas.getLocationOnScreen();

        int centerX = screenLocation.x + bounds.width / 2;
        int centerY = screenLocation.y + bounds.height / 2;

        int targetX = -centerX + (int) (screenLocation.x + x);
        int targetY = -centerY + (int) (screenLocation.y + y);

        robot.moveMouse(targetX + centerX, targetY + centerY);
    }

    @Override
    public void moveCursorOnCanvas() {
        moveToNode(CANVAS_ROOT);
    }

    @Override
    public void mousePrimaryClick() {
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
    }

    @Override
    public void dragAndDrop(final double x, final double y) {
        performAndHold(() -> {
            Point p = MouseInfo.getPointerInfo().getLocation();

            robot.pressMouse(MouseButton.LEFT_BUTTON);
            robot.moveMouse((int) (p.x + x), (int) (p.y + y));
            robot.releaseMouse(MouseButton.LEFT_BUTTON);
            /*drag(MouseButton.PRIMARY).moveBy(x, y).drop()*/
        });
    }

    @Override
    public void hold() {
        Pause.pause(2, TimeUnit.SECONDS);
    }

    private void moveToNode(final Enum<ElementID> id) {
        performAndHold(() -> {
            final Component c = robot.finder().findByName(id.toString());
            robot.moveMouse(c);
        });
    }

    private void clickOnNode(final Enum<ElementID> id) {
        performAndHold(() -> {
            final Component c = robot.finder().findByName(id.toString());
            robot.click(c);
        });
    }

    private void performAndHold(final Runnable runnable) {
        hold();
        runnable.run();
    }
}
