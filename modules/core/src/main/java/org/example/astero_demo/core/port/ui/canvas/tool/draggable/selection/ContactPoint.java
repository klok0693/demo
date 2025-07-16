package org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection;

import org.example.astero_demo.core.port.ui.canvas.tool.ShapeSelectionTool;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.DraggableTool;

/**
 * Represent a special contact points, which allows {@link ShapeSelectionTool} to modify shape dimensions.<p>
 * Every point has it own {@link ContactAlignment}, based on which all necessary calculations are made
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface ContactPoint<E extends Object> extends DraggableTool<E> {

    void setValues(double x, double y, double width, double height);
}
