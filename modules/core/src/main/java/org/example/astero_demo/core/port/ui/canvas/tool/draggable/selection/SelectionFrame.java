package org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection;

import org.example.astero_demo.api.graphics.GraphicsPainter;
import org.example.astero_demo.core.port.ui.canvas.tool.CanvasTool;

import static org.example.astero_demo.core.port.ui.UIConstants.MINIMAL_SIDE_SIZE;

public abstract class SelectionFrame<E extends GraphicsPainter> extends CanvasTool<E> {

    protected SelectionFrame() {
        super(-1, -1, -1, -1, 0);
        setEnabled(true);
    }

    public void update(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = Math.max(width, MINIMAL_SIDE_SIZE);
        this.height = Math.max(height, MINIMAL_SIDE_SIZE);
        setVisible(true);
    }

    public boolean isInBounds(final double x, final double y) {
        return x >= this.x && x <= this.x + this.width
                && y >= this.y && y <= this.y + this.height;
    }
}
