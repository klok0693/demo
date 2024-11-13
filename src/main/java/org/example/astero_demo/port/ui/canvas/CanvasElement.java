package org.example.astero_demo.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;

public abstract class CanvasElement implements Drawable {
    protected double x, y, width, height;

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
