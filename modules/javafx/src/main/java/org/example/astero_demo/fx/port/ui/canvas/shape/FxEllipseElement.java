package org.example.astero_demo.fx.port.ui.canvas.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.port.ui.canvas.shape.EllipseElement;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;

/**
 * JavaFX's realization of {@link EllipseElement}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxEllipseElement extends EllipseElement<GraphicsContext> implements FxCanvasElement {
    protected Color fillColor;

    protected FxEllipseElement(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color fillColor) {
        super(layer, modelRelatedId, x, y, width, height);
        this.fillColor = fillColor;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(fillColor);
        gc.fillOval(x, y, width, height);
    }

    @Override
    public void save(final GraphicsContext gc) {
        FxCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final GraphicsContext gc) {
        FxCanvasElement.super.restore(gc);
    }
}
