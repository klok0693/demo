package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class DraggableTool extends CanvasTool implements CanvasDraggable {
    protected static final double OPACITY = 0.4;
    protected boolean isActive = false;

    protected DraggableTool(final int layer) {
        super(-1, -1, -1, -1, layer);
    }

    protected DraggableTool(final double width, final double height, final int layer) {
        super(-1, -1, width, height, layer);
    }

    @Override
    public void onMouseDragged(double x, double y) {
        if (!isActive) {
            return;
        }
        update(x, y);
    }

    protected abstract void update(double x, double y);

    @Override
    public void onMouseReleased(final MouseEvent event) {
        if (!isActive) {
            return;
        }
        final double[] values = reset();
        performOperation(values);
    }

    protected abstract void performOperation(double[] toolValues);

    @Override
    public double[] reset() {
        this.isActive = false;
        return super.reset();
    }
}
