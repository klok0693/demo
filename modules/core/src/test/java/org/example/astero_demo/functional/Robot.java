package org.example.astero_demo.functional;

public interface Robot {

    void clickOnNode(String id);

    void moveToNode(String id);

    void dragAndDropBy(double x, double y);

    void hold();
}
