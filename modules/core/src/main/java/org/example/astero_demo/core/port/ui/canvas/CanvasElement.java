package org.example.astero_demo.core.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

/**
 * Represents an abstract element, that can be drawn on the canvas.<p>
 * Unlike layers, cannot be nested within each other, being leaves<p>
 * in the tree of elements on the canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public abstract class CanvasElement implements Drawable {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    protected CanvasElement(
            final double x,
            final double y,
            final double width,
            final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(final GraphicsContext gc) {
        gc.save();
        drawElement(gc);
        gc.restore();
    }

    protected abstract void drawElement(GraphicsContext gc);
}
