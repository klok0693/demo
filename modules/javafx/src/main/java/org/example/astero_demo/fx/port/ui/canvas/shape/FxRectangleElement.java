package org.example.astero_demo.fx.port.ui.canvas.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.port.ui.canvas.shape.RectangleElement;

/**
 * JavaFX's realization of {@link RectangleElement}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxRectangleElement extends FxShapeElement implements RectangleElement<GraphicsContext> {
    //TODO: Someday, sometime
    private double opacity;
    private double scale;
    private double angle;
    private double pivotX, pivotY;

    public FxRectangleElement(
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
