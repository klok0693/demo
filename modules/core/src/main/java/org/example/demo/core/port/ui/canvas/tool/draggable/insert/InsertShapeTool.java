package org.example.demo.core.port.ui.canvas.tool.draggable.insert;

import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.api.graphics.color.Color;
import org.example.demo.api.graphics.color.Colors;
import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.CanvasClickable;
import org.example.demo.core.port.ui.canvas.tool.draggable.DraggableTool;

/**
 * Represents a tool for inserting shapes on a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class InsertShapeTool<E extends GraphicsPainter> extends DraggableTool<E> implements CanvasClickable {
    protected Color fillColor;

    protected InsertShapeTool(final CanvasAdapter adapter, final UIState uiState) {
        super(2, adapter, uiState);
        this.fillColor = Colors.BLUE;
    }

    @Override
    protected void drawElement(final E gc) {
        gc.setFill(fillColor);
        gc.setOpacity(OPACITY);

        switch (uiState.getInsertShapeType()) {
            case RECT -> gc.fillRect(x, y, width, height);
            case ELLIPSE -> gc.fillOval(x, y, width, height);
        }
    }

    @Override
    public void onMousePressed(final double mouseX, final double mouseY, final boolean isShiftDown) {
        if (!isEnabled()) {
            return;
        }
        this.x = mouseX;
        this.y = mouseY;
        setActive(true);
    }

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        return isEnabled() && isActive();
    }

    @Override
    public void update(final double x, final double y) {
        this.width = x - this.x;
        this.height = y - this.y;
        setVisible(true);
    }

    @Override
    public void performOperation(final double[] toolValues) {
        adapter.createNewShapeAt(toolValues[0], toolValues[1], toolValues[2], toolValues[3]);
    }
}
