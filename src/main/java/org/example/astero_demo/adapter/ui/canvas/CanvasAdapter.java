package org.example.astero_demo.adapter.ui.canvas;

import org.example.astero_demo.adapter.ui.UpdatableAdapter;

/**
 * Represents an adapter for interact with a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CanvasAdapter extends UpdatableAdapter {

    void primaryMouseBtnPressed(double x, double y);

    void selectNextShapeAt(double x, double y);

    void createNewShapeAt(double x, double y, double width, double height);

    void modifySelectedShape(double x, double y, double width, double height);

    void moveSelectedShapeTo(double x, double y);

    double[] getLocalCursorPosition();
}
