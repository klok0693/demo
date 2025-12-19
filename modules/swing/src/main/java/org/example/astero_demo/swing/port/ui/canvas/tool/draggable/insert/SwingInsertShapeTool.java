package org.example.astero_demo.swing.port.ui.canvas.tool.draggable.insert;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert.InsertShapeTool;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

import java.awt.*;

/**
 * JavaFX's realization of {@link InsertShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingInsertShapeTool extends InsertShapeTool<SwingPainter> implements SwingCanvasElement {

    public SwingInsertShapeTool(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
    }
}
