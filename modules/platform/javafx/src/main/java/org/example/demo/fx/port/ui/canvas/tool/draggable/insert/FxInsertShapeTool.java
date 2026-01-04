package org.example.demo.fx.port.ui.canvas.tool.draggable.insert;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.draggable.insert.InsertShapeTool;
import org.example.demo.fx.port.ui.graphics.FxPainter;

/**
 * JavaFX's realization of {@link InsertShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxInsertShapeTool extends InsertShapeTool<FxPainter> {

    public FxInsertShapeTool(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
    }
}
