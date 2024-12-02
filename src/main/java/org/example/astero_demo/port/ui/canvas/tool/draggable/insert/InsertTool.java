package org.example.astero_demo.port.ui.canvas.tool.draggable.insert;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.tool.CanvasClickable;
import org.example.astero_demo.port.ui.canvas.tool.draggable.DraggableTool;

/**
 * Represents a tool for inserting shapes on a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class InsertTool extends DraggableTool implements CanvasClickable {
    private final CanvasAdapter adapter;
    private final UIState uiState;

    public InsertTool(final CanvasAdapter adapter, final UIState uiState) {
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
    protected void update(double x, double y) {
        this.width = x - this.x;
        this.height = y - this.y;
        setVisible(true);
    }

    @Override
    protected void performOperation(final double[] toolValues) {
        adapter.createNewShapeAt(toolValues[0], toolValues[1], toolValues[2], toolValues[3]);
    }
}
