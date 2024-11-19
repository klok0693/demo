package org.example.astero_demo.port.ui.canvas.tool.draggable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.tool.CanvasClickable;

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
        if (!isVisible) {
            return;
        }
        gc.setFill(fillColor);
        gc.setGlobalAlpha(OPACITY);

        switch (uiState.getInsertShapeType()) {
            case RECT -> gc.fillRect(x, y, width, height);
            case ELLIPSE -> gc.fillOval(x, y, width, height);
        }
    }

    @Override
    public void onMousePressed(final double x, final double y) {
        this.x = x;
        this.y = y;
        this.isActive = true;
    }

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        return isActive;
    }

    @Override
    protected void update(double x, double y) {
        this.width = x - this.x;
        this.height = y - this.y;
        this.isVisible = true;
    }

    @Override
    protected void performOperation(final double[] toolValues) {
        adapter.createNewShapeAt(toolValues[0], toolValues[1], toolValues[2], toolValues[3]);
    }
}
