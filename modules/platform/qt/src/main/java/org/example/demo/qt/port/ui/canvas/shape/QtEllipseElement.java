package org.example.demo.qt.port.ui.canvas.shape;

import org.example.demo.api.graphics.color.Color;
import org.example.demo.core.port.ui.canvas.shape.EllipseElement;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * Qt's realization of {@link EllipseElement}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtEllipseElement extends EllipseElement<QtPainter> {

    protected QtEllipseElement(
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
