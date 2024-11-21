package org.example.astero_demo.port.ui.canvas.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleElement extends ShapeElement {
    private double opacity;
    private double scale;
    private double angle;
    private double pivotX, pivotY;

    public RectangleElement(
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
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(fillColor);
        gc.fillRect(x, y, width, height);
    }
}
