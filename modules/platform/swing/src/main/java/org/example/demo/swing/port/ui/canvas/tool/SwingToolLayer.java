package org.example.demo.swing.port.ui.canvas.tool;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.ToolLayer;
import org.example.demo.swing.port.ui.canvas.tool.draggable.drag.SwingDragShapeTool;
import org.example.demo.swing.port.ui.canvas.tool.draggable.insert.SwingInsertShapeTool;
import org.example.demo.swing.port.ui.canvas.tool.draggable.selection.SwingShapeSelectionTool;
import org.example.demo.swing.port.ui.graphics.SwingPainter;

/**
 * Swing realization of {@link ToolLayer}. Necessary, because it<p>
 * hold a link to {@link SwingPainter}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingToolLayer extends ToolLayer<SwingPainter> {

    public SwingToolLayer(
            final SwingShapeSelectionTool selectionTool,
            final SwingDragShapeTool dragTool,
            final SwingInsertShapeTool insertTool,
            final UIState uiState) {
        super(selectionTool, dragTool, insertTool, uiState);
    }
}
