package org.example.demo.swing.port.ui.canvas.tool.draggable.insert;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.draggable.insert.InsertShapeTool;
import org.example.demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.demo.swing.port.ui.graphics.SwingPainter;

/**
 * Swing realization of {@link InsertShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingInsertShapeTool extends InsertShapeTool<SwingPainter> implements SwingCanvasElement {

    public SwingInsertShapeTool(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
    }
}
