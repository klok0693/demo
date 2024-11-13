package org.example.astero_demo.port.ui.canvas.element;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.port.ui.canvas.ShapeUI;

public class RectangleDrawable extends ShapeUI {
    private double x, y, width, height;
    private Color fillColor;
    private double opacity;
    private double scale;
    private double angle;

    public RectangleDrawable(final double x, final double y, final double width, final double height, final Color fillColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setFill(fillColor);
        gc.fillRect(x, y, width, height);
        gc.restore();
    }

    @Override
    public void destroyLinks() {}
}
