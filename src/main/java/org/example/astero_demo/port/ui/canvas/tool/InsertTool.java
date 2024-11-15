package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lombok.Setter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.CanvasView;

public class InsertTool extends DraggableTool implements CanvasClickable {
    private final CanvasView canvasView;
    @Setter
    private UIState uiState;

    protected InsertTool(final CanvasView canvasView, final int layer) {
        super(-1, -1, -1, -1, layer);
        this.canvasView = canvasView;
    }

    @Override
    protected void drawElement(GraphicsContext gc) {
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
    public void destroyLinks() {

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
    public void onMouseDragged(final MouseEvent event) {
        if (!isActive) {
            return;
        }
        this.width = event.getX() - this.x;
        this.height = event.getY() - this.y;

        this.isVisible = true;
    }

    @Override
    public void onMouseReleased(final MouseEvent event) {
        this.isVisible = false;
        if (!isActive) {
            return;
        }
        canvasView.onDragOver(this.x, this.y, this.width, this.height);
        reset();
    }
}
