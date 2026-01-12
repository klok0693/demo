package org.example.demo.qt.port.ui.canvas.tool;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.ToolLayer;
import org.example.demo.qt.port.ui.canvas.tool.draggable.drag.QtDragShapeTool;
import org.example.demo.qt.port.ui.canvas.tool.draggable.insert.QtInsertShapeTool;
import org.example.demo.qt.port.ui.canvas.tool.draggable.selection.QtShapeSelectionTool;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * Qt's realization of {@link ToolLayer}. Necessary, because it<p>
 * hold a link to {@link QtPainter}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtToolLayer extends ToolLayer<QtPainter> {

    public QtToolLayer(
            final QtShapeSelectionTool selectionTool,
            final QtDragShapeTool dragTool,
            final QtInsertShapeTool insertTool,
            final UIState uiState) {
        super(selectionTool, dragTool, insertTool, uiState);
    }
}
