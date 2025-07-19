package org.example.astero_demo.fx.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.ToolLayer;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.drag.FxDragShapeTool;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.insert.FxInsertShapeTool;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.selection.FxShapeSelectionTool;

/**
 * JavaFX's realization of {@link ToolLayer}. Necessary, because it<p>
 * hold a link to {@link GraphicsContext}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxToolLayer extends ToolLayer<GraphicsContext> {

    public FxToolLayer(
            final FxShapeSelectionTool selectionTool,
            final FxDragShapeTool dragTool,
            final FxInsertShapeTool insertTool,
            final UIState uiState) {
        super(selectionTool, dragTool, insertTool, uiState);
    }
}
