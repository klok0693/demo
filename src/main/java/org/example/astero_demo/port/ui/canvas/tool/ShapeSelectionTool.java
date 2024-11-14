package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.port.ui.canvas.CanvasElement;

public class ShapeSelectionTool extends CanvasTool {

    public ShapeSelectionTool(
            final double x,
            final double y,
            final double width,
            final double height) {
        super(x, y, width, height, 0);
    }

    public void update(
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
    protected void drawElement(final GraphicsContext gc) {
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);
        gc.strokeRect(x, y, width, height);
    }

    @Override
    public void destroyLinks() {}
}
