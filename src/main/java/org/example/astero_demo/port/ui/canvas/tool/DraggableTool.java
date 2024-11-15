package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class DraggableTool extends CanvasTool implements CanvasDraggable {
    protected static final double OPACITY = 0.4;
    protected boolean isActive = false;

    protected DraggableTool(
            final double x,
            final double y,
            final double width,
            final double height,
            final int layer) {
        super(x, y, width, height, layer);
    }

    @Override
    public void reset() {
        super.reset();
        this.isActive = false;
    }
}
