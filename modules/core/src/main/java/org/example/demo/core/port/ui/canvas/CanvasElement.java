package org.example.demo.core.port.ui.canvas;

import lombok.Getter;
import lombok.Setter;
import org.example.demo.api.graphics.Drawable;
import org.example.demo.api.graphics.GraphicsPainter;

/**
 * Represents an abstract element, that can be drawn on the canvas.<p>
 * Unlike layers, cannot be nested within each other, being leaves<p>
 * in the tree of elements on the canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
@Setter
public abstract class CanvasElement<E extends GraphicsPainter> implements Drawable<E> {
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
    public void draw(final E gc) {
        drawElement(gc);
    }

    protected abstract void drawElement(E gc);
}
