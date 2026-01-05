package org.example.demo.qt.port.ui.canvas.tool.draggable.drag;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.qt.port.ui.graphics.QtPainter;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.tool.draggable.drag.DragShapeTool;
import org.example.demo.qt.port.ui.canvas.QtCanvasElement;

/**
 * JavaFX's realization of {@link DragShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtDragShapeTool extends DragShapeTool<QtPainter> implements QtCanvasElement {

    public QtDragShapeTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        super(adapter, modelState, uiState);
    }
}
