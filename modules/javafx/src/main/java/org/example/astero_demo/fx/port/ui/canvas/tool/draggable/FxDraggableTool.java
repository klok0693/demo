package org.example.astero_demo.fx.port.ui.canvas.tool.draggable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.DraggableTool;
import org.example.astero_demo.fx.port.ui.canvas.tool.FxCanvasTool;

/**
 * JavaFX's realization of {@link DraggableTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public abstract class FxDraggableTool extends FxCanvasTool implements DraggableTool<GraphicsContext>, CanvasDraggable {
    protected static final double OPACITY = 0.4;
    protected Color fillColor;

    protected FxDraggableTool(final int layer) {
        super(-1, -1, -1, -1, layer);
    }

    protected FxDraggableTool(final double width, final double height, final int layer) {
        super(-1, -1, width, height, layer);
    }

    @Override
    public void onMouseReleased(final MouseEvent event) {
        if (!isEnabled() || !isActive()) {
            return;
        }
        final double[] values = reset();
        performOperation(values);
    }

    @Override
    public void onMouseDragged(final double x, final double y) {
        if (!isEnabled() || !isActive()) {
            return;
        }
        update(x, y);
    }
}
