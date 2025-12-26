package org.example.demo.fx.port.ui.canvas.shape;

import org.example.demo.api.graphics.color.Color;
import org.example.demo.core.port.ui.canvas.shape.RectangleElement;
import org.example.demo.fx.port.ui.canvas.FxCanvasElement;
import org.example.demo.fx.port.ui.graphics.FxPainter;

/**
 * JavaFX's realization of {@link RectangleElement}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxRectangleElement extends RectangleElement<FxPainter> implements FxCanvasElement {
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
    public void draw(final FxPainter gc) {
        save(gc);
        super.draw(gc);
        restore(gc);
    }
}
