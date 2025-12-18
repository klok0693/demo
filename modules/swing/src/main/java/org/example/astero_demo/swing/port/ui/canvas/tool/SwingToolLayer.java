package org.example.astero_demo.swing.port.ui.canvas.tool;

import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.ToolLayer;
import org.example.astero_demo.swing.port.ui.canvas.tool.draggable.drag.SwingDragShapeTool;
import org.example.astero_demo.swing.port.ui.canvas.tool.draggable.insert.SwingInsertShapeTool;
import org.example.astero_demo.swing.port.ui.canvas.tool.draggable.selection.SwingShapeSelectionTool;

import java.awt.*;

/**
 * JavaFX's realization of {@link ToolLayer}. Necessary, because it<p>
 * hold a link to {@link GraphicsContext}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingToolLayer extends ToolLayer<Graphics> {

    public SwingToolLayer(
            final SwingShapeSelectionTool selectionTool,
            final SwingDragShapeTool dragTool,
            final SwingInsertShapeTool insertTool,
            final UIState uiState) {
        super(selectionTool, dragTool, insertTool, uiState);
    }
}
