package org.example.astero_demo.fx.port.ui.canvas.tool.draggable.insert;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.DraggableTool;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert.InsertShapeTool;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;

/**
 * JavaFX's realization of {@link InsertShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxInsertShapeTool extends InsertShapeTool<GraphicsContext> implements FxCanvasElement {
    protected Color fillColor;

    public FxInsertShapeTool(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
        this.fillColor = Color.BLUE;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(fillColor);
        gc.setGlobalAlpha(OPACITY);

        switch (uiState.getInsertShapeType()) {
            case RECT -> gc.fillRect(x, y, width, height);
            case ELLIPSE -> gc.fillOval(x, y, width, height);
        }
    }

    @Override
    public void save(final GraphicsContext gc) {
        FxCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final GraphicsContext gc) {
        FxCanvasElement.super.restore(gc);
    }
}
