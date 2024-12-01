package org.example.astero_demo.adapter.ui.canvas;

import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.state.mode.InsertModeSwitchable;
import org.example.astero_demo.adapter.ui.state.mode.SingleSelectionModeSwitchable;

/**
 * Represents a view for a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CanvasView extends UpdatableView, InsertModeSwitchable, SingleSelectionModeSwitchable {

    double[] getLocalCursorPosition();
}
