package org.example.astero_demo.core.adapter.ui.canvas;

import org.example.astero_demo.core.adapter.ui.UpdatableAdapter;
import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitcher;
import org.example.astero_demo.core.adapter.ui.state.mode.UIMode;

/**
 * Represents an adapter for interact with a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CanvasAdapter extends UpdatableAdapter, ModeSwitcher {

    void primaryMouseBtnPressed(double x, double y, boolean isAdditional);

    void selectNextShapeAt(double x, double y);

    void selectMultiple(double x, double y);

    void createNewShapeAt(double x, double y, double width, double height);

    void modifySelectedShape(double x, double y, double width, double height);

    void moveSelectedShapeTo(double x, double y);

    double[] getLocalCursorPosition();
}
