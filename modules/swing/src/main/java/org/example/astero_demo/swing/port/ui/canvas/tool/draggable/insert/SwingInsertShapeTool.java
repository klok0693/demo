package org.example.astero_demo.swing.port.ui.canvas.tool.draggable.insert;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert.InsertShapeTool;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;

import java.awt.*;

/**
 * JavaFX's realization of {@link InsertShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingInsertShapeTool extends InsertShapeTool<Graphics> implements SwingCanvasElement {
    protected Color fillColor;

    public SwingInsertShapeTool(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
        this.fillColor = Color.BLUE;
    }

    @Override
    protected void drawElement(final Graphics gc) {
/*        gc.setFill(fillColor);
        gc.setGlobalAlpha(OPACITY);

        switch (uiState.getInsertShapeType()) {
            case RECT -> gc.fillRect(x, y, width, height);
            case ELLIPSE -> gc.fillOval(x, y, width, height);
        }*/
    }

    @Override
    public void save(final Graphics gc) {
        SwingCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final Graphics gc) {
        SwingCanvasElement.super.restore(gc);
    }
}
