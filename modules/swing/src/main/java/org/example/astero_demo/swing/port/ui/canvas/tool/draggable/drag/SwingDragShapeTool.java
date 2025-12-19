package org.example.astero_demo.swing.port.ui.canvas.tool.draggable.drag;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.drag.DragShapeTool;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;
import org.example.astero_demo.swing.util.ColorUtils;

import java.awt.*;

/**
 * JavaFX's realization of {@link DragShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingDragShapeTool extends DragShapeTool<SwingPainter> implements SwingCanvasElement {

    public SwingDragShapeTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        super(adapter, modelState, uiState);
    }
}
