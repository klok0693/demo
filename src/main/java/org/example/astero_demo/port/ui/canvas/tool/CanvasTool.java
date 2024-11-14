package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.port.ui.canvas.CanvasElement;

public abstract class CanvasTool extends CanvasElement implements Comparable<CanvasTool> {
    private final int layer;

    protected CanvasTool(
            final double x,
            final double y,
            final double width,
            final double height,
            final int layer) {
        super(x, y, width, height);
        this.layer = layer;
    }

    @Override
    public int compareTo(CanvasTool o) {
        return Integer.compare(this.layer, o.layer);
    }
}
