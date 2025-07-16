package org.example.astero_demo.core.port.ui.canvas.tool.draggable;

import org.example.astero_demo.core.port.ui.canvas.tool.CanvasTool;

/**
 * Draggable tool on a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface DraggableTool<E extends Object> extends CanvasTool<E> {

    void update(double x, double y);

    void performOperation(double[] toolValues);
}
