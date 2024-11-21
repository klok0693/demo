package org.example.astero_demo.port.ui.canvas.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Ellipse element
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class EllipseElement extends ShapeElement {

    protected EllipseElement(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color fillColor) {
        super(layer, modelRelatedId, x, y, width, height, fillColor);
    }

    @Override
    protected void drawElement(GraphicsContext gc) {
        gc.setFill(fillColor);
        gc.fillOval(x, y, width, height);
    }
}
