package org.example.astero_demo.fx.port.ui.canvas.tool.draggable.insert;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert.InsertShapeTool;
import org.example.astero_demo.fx.port.ui.canvas.tool.CanvasClickable;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.FxDraggableTool;

/**
 * JavaFX's realization of {@link InsertShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxInsertShapeTool extends FxDraggableTool implements InsertShapeTool<GraphicsContext>, CanvasClickable {
    private final CanvasAdapter adapter;
    private final UIState uiState;

    public FxInsertShapeTool(final CanvasAdapter adapter, final UIState uiState) {
        super(2);
        this.adapter = adapter;
        this.uiState = uiState;
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
    public void onMousePressed(final MouseEvent event) {
        if (!isEnabled()) {
            return;
        }
        this.x = event.getX();
        this.y = event.getY();
        setActive(true);
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        return isEnabled() && isActive();
    }

    @Override
    public void update(double x, double y) {
        this.width = x - this.x;
        this.height = y - this.y;
        setVisible(true);
    }

    @Override
    public void performOperation(final double[] toolValues) {
        adapter.createNewShapeAt(toolValues[0], toolValues[1], toolValues[2], toolValues[3]);
    }
}
