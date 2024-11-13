package org.example.astero_demo.port.ui.canvas.element;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleElement extends ShapeElement {
    private Color fillColor;
    private double opacity;
    private double scale;
    private double angle;

    public RectangleElement(
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color fillColor) {
        super(modelRelatedId, x, y, width, height);
        this.fillColor = fillColor;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(fillColor);
        gc.fillRect(x, y, width, height);
    }

    @Override
    public void destroyLinks() {}
}
