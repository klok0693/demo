package org.example.astero_demo.core.port.ui.canvas.tool.draggable.drag;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.DraggableTool;
import org.example.astero_demo.core.util.ColorUtils;

import static java.lang.Double.parseDouble;

/**
 * Represents a tool used for dragging shapes on a canvas.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface DragShapeTool<E extends Object> extends DraggableTool<E> {


}
