package org.example.astero_demo.core.port.ui.canvas.shape;

import org.example.astero_demo.api.graphics.GraphicsPainter;
import org.example.astero_demo.api.graphics.color.Color;

/**
 * Rectangle element
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class RectangleElement<E extends GraphicsPainter> extends ShapeElement<E> {

    protected RectangleElement(
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
        gc.fillRect(x, y, width, height);
    }
}
