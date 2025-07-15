package org.example.astero_demo.fx.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;

@Getter
@Setter
public abstract class FxCanvasElement implements CanvasElement<GraphicsContext> {

    protected double x;
    protected double y;
    protected double width;
    protected double height;

    protected FxCanvasElement(
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
