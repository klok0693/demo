package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;

public class InsertTool extends DraggableTool implements CanvasClickable {
    private final CanvasAdapter adapter;
    private final UIState uiState;

    public InsertTool(final CanvasAdapter adapter, final UIState uiState) {
        super(2);
        this.adapter = adapter;
        this.uiState = uiState;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        if (!isVisible) {
            return;
        }
        gc.setFill(Color.GREEN);
        gc.setGlobalAlpha(OPACITY);

        switch (uiState.getInsertShapeType()) {
            case RECT:
                gc.fillRect(x, y, width, height);
                break;
            case OVAL:
                gc.fillOval(x, y, width, height);
                break;
        }
    }

    @Override
    public void onMousePressed(final double x, final double y) {
        this.x = x;
        this.y = y;
        this.isActive = true;
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        return isActive;
    }

    @Override
    protected void update(double x, double y) {
        this.width = x - this.x;
        this.height = y - this.y;
        this.isVisible = true;
    }

    @Override
    public void onMouseReleased(final MouseEvent event, final boolean isOnBounds) {
        this.isVisible = false;
        if (!isActive) {
            return;
        }
        adapter.onDragOver(this.x, this.y, this.width, this.height);
        reset();
    }
}
