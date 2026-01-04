package org.example.demo.fx.port.ui.canvas.tool.draggable.drag;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.fx.port.ui.graphics.FxPainter;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.tool.draggable.drag.DragShapeTool;

/**
 * JavaFX's realization of {@link DragShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxDragShapeTool extends DragShapeTool<FxPainter> {

    public FxDragShapeTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        super(adapter, modelState, uiState);
    }
}
