package org.example.demo.core.port.ui.canvas.shape;

import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.api.graphics.color.Color;

/**
 * Ellipse element
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class EllipseElement<E extends GraphicsPainter> extends ShapeElement<E> {

    protected EllipseElement(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color color) {
        super(layer, modelRelatedId, x, y, width, height, color);
    }

    @Override
    protected void drawElement(final GraphicsPainter gc) {
        gc.setFill(fillColor);
        gc.fillOval(x, y, width, height);
    }
}
