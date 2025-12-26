package org.example.demo.functional;

public interface Robot {

    void clickOnCreateRectBtn();

    void clickOnDeleteRectBtn();

    void moveCursorOnCanvas();

    void mousePrimaryClick();

    void moveCursorOnCanvasBy(double x, double y);

    void dragAndDrop(double x, double y);

    void hold();
}
