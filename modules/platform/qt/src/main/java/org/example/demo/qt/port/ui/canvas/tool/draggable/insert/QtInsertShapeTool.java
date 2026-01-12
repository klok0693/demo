package org.example.demo.qt.port.ui.canvas.tool.draggable.insert;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.draggable.insert.InsertShapeTool;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * Qt's realization of {@link InsertShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtInsertShapeTool extends InsertShapeTool<QtPainter> {

    public QtInsertShapeTool(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
    }
}
