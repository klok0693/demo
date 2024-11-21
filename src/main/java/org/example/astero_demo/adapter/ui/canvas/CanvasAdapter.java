package org.example.astero_demo.adapter.ui.canvas;

import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.ui.UpdatableAdapter;

public interface CanvasAdapter extends UpdatableAdapter {

    void primaryMouseBtnPressed(double x, double y);

    void createNewShapeAt(double x, double y, double width, double height);

    void modifySelectedShape(double x, double y, double width, double height);

    void moveSelectedShapeTo(double x, double y);
}
