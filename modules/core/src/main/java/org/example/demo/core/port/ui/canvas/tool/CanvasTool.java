package org.example.demo.core.port.ui.canvas.tool;

import lombok.Getter;
import lombok.Setter;
import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.core.port.ui.canvas.CanvasElement;

/**
 * Base class for all canvas tools. Tool is a specific element, provided<p>
 * ability to perform various operations with canvas shape elements
 * <p>
 * <b>Note:</b> this class has a natural ordering that is inconsistent with equals.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
@Setter
public abstract class CanvasTool<E extends GraphicsPainter> extends CanvasElement<E> implements Comparable<CanvasTool<E>> {
    private final int layer;
    private boolean isVisible;
    private boolean isActive;
    private boolean isEnabled;

    protected CanvasTool(
            final double x,
            final double y,
            final double width,
            final double height,
            final int layer) {
        super(x, y, width, height);
        this.layer = layer;
        this.isVisible = false;
        this.isActive = false;
        this.isEnabled = false;
    }

    @Override
    public void draw(final E gc) {
        if (isEnabled && isVisible) {
            super.draw(gc);
        }
    }

    /**
     * Every class, adding new mutable fields, must override this method
     */
    public double[] reset() {
        final double[] values = new double[] {this.x, this.y, this.width, this.height};
        this.x = -1;
        this.y = -1;
        this.width = -1;
        this.height = -1;
        this.isVisible = false;
        this.isActive = false;
        return values;
    }

    @Override
    public int compareTo(final CanvasTool o) {
        return Integer.compare(this.layer, o.getLayer());
    }
}
