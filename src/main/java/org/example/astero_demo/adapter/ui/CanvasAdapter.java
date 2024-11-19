package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.model.entity.Shape;

public interface CanvasAdapter {

    void primaryMouseBtnPressed(double x, double y);

    void createNewShapeAt(double x, double y, double width, double height);

    void modifySelectedShape(double x, double y, double width, double height);

    void moveSelectedShapeTo(double x, double y);

    Shape selectElement(double x, double y);

    Shape selectElement(int id);

    void setParent(ParentAdapter parentAdapter);

    void update();
}
