package org.example.demo.functional;

/**
 * TODO: Split to several specialized robots
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface Robot {

    void clickOnCreateRectBtn();

    void clickOnDeleteRectBtn();

    void moveCursorOnCanvas();

    void mousePrimaryClick();

    void moveCursorOnCanvasBy(double x, double y);

    void dragAndDrop(double x, double y);

    void hold();
}
