package org.example.astero_demo.core.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;

/**
 * Base class for all canvas tools. Tool is a specific element, provided<p>
 * ability to perform various operations with canvas shape elements
 * <p>
 * <b>Note:</b> this class has a natural ordering that is inconsistent with equals.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class CanvasTool extends CanvasElement implements Comparable<CanvasTool> {
    private final int layer;
    @Getter @Setter
    private boolean isVisible;
    @Getter @Setter
    private boolean isActive;
    @Getter @Setter
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
    public void draw(final GraphicsContext gc) {
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
        return Integer.compare(this.layer, o.layer);
    }
}
