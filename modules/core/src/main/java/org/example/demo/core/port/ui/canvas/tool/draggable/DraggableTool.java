package org.example.demo.core.port.ui.canvas.tool.draggable;

import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.CanvasDraggable;
import org.example.demo.core.port.ui.canvas.tool.CanvasTool;

/**
 * Draggable tool on a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class DraggableTool<E extends GraphicsPainter> extends CanvasTool<E> implements CanvasDraggable {
    protected static final double OPACITY = 0.4;

    protected final CanvasAdapter adapter;
    protected final UIState uiState;

    protected DraggableTool(
            final int layer,
            final CanvasAdapter adapter,
            final UIState uiState) {
        super(-1, -1, -1, -1, layer);
        this.adapter = adapter;
        this.uiState = uiState;
    }

    protected DraggableTool(
            final double width,
            final double height,
            final int layer,
            final CanvasAdapter adapter,
            final UIState uiState) {
        super(-1, -1, width, height, layer);
        this.adapter = adapter;
        this.uiState = uiState;
    }

    @Override
    public void onMouseReleased(final double mouseX, final double mouseY) {
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

    protected abstract void update(double x, double y);

    protected abstract void performOperation(double[] toolValues);
}
