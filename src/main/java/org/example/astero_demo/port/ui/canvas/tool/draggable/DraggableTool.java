package org.example.astero_demo.port.ui.canvas.tool.draggable;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.port.ui.canvas.tool.CanvasTool;

/**
 * Draggable tool on a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class DraggableTool extends CanvasTool implements CanvasDraggable {
    protected static final double OPACITY = 0.4;
    protected Color fillColor;

    protected DraggableTool(final int layer) {
        super(-1, -1, -1, -1, layer);
    }

    protected DraggableTool(final double width, final double height, final int layer) {
        super(-1, -1, width, height, layer);
    }

    @Override
    public void onMouseDragged(final double x, final double y) {
        if (!isEnabled() || !isActive()) {
            return;
        }
        update(x, y);
    }

    protected abstract void update(double x, double y);

    @Override
    public void onMouseReleased(final MouseEvent event) {
        if (!isEnabled() || !isActive()) {
            return;
        }
        final double[] values = reset();
        performOperation(values);
    }

    protected abstract void performOperation(double[] toolValues);
}
