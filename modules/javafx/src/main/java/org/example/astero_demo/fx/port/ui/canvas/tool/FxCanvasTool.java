package org.example.astero_demo.fx.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.example.astero_demo.core.port.ui.canvas.tool.CanvasTool;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;

@Getter
@Setter
public abstract class FxCanvasTool extends FxCanvasElement implements CanvasTool<GraphicsContext> {
    private final int layer;
    private boolean isVisible;
    private boolean isActive;
    private boolean isEnabled;

    protected FxCanvasTool(
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
    @Override
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
