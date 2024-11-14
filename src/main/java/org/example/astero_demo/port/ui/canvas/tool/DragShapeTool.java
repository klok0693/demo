package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Setter;
import org.example.astero_demo.port.ui.canvas.CanvasElement;

import java.util.Arrays;

public class DragShapeTool extends CanvasTool {
    private static final double OPACITY = 0.4;

    private final Color color;

    private final double xOffset;
    private final double yOffset;

    public DragShapeTool(
            final double x,
            final double y,
            final double width,
            final double height,
            final Color color,
            final double xOffset,
            final double yOffset) {
        super(x, y, width, height, 1);
        this.color = color;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(color);
        gc.setGlobalAlpha(OPACITY);
        gc.fillRect(x - xOffset, y - yOffset, width, height);
    }

    public void update(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double[] getCurrentPosition() {
        return new double[]{x - xOffset, y - yOffset};
    }

    @Override
    public void destroyLinks() {

    }
}
