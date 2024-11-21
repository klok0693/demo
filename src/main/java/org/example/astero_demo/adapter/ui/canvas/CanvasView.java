package org.example.astero_demo.adapter.ui.canvas;

import org.example.astero_demo.adapter.ui.UpdatableView;

/**
 * Represents a view for a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CanvasView extends UpdatableView {

    double[] getLocalCursorPosition();
}
