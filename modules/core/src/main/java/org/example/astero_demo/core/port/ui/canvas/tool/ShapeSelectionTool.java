package org.example.astero_demo.core.port.ui.canvas.tool;

import org.example.astero_demo.core.adapter.ui.UpdatableView;

/**
 * Canvas tool, allowing to select shape element on a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface ShapeSelectionTool<E extends Object> extends CanvasTool<E>, CanvasClickable, UpdatableView {

    boolean isInBounds(double x, double y);
}
