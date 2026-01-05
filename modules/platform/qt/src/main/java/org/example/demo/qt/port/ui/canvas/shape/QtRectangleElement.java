package org.example.demo.qt.port.ui.canvas.shape;

import org.example.demo.api.graphics.color.Color;
import org.example.demo.core.port.ui.canvas.shape.RectangleElement;
import org.example.demo.qt.port.ui.canvas.QtCanvasElement;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * JavaFX's realization of {@link RectangleElement}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtRectangleElement extends RectangleElement<QtPainter> implements QtCanvasElement {
    //TODO: Someday, sometime
    private double opacity;
    private double scale;
    private double angle;
    private double pivotX, pivotY;

    public QtRectangleElement(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color fillColor) {
        super(layer, modelRelatedId, x, y, width, height, fillColor);
    }
}
