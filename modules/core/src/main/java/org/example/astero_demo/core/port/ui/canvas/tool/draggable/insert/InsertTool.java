package org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.CanvasClickable;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.DraggableTool;

/**
 * Represents a tool for inserting shapes on a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface InsertTool<E extends Object> extends DraggableTool<E>, CanvasClickable {

}
