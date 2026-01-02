package org.example.demo.qt.port.ui.canvas.shape;

import org.example.demo.api.graphics.color.Colors;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.CanvasElement;
import org.example.demo.core.port.ui.canvas.CanvasLayer;
import org.example.demo.core.port.ui.canvas.shape.EllipseElement;
import org.example.demo.core.port.ui.canvas.shape.RectangleElement;
import org.example.demo.core.port.ui.canvas.shape.ShapeLayer;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * JavaFX's realization of {@link ShapeLayer}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtShapeLayer extends ShapeLayer<QtPainter> {

    public QtShapeLayer(final ModelState modelState) {
        super(modelState);
    }

    public QtShapeLayer(final int layer, final ModelState modelState) {
        super(layer, modelState);
    }

    @Override
    protected EllipseElement<QtPainter> createEllipse(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final String fillColor) {
        return new QtEllipseElement(layer, modelRelatedId, x, y, width, height, Colors.convert(fillColor));
    }


    @Override
    protected RectangleElement<QtPainter> createRectangle(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final String fillColor) {
        return new QtRectangleElement(layer, modelRelatedId, x, y, width, height, Colors.convert(fillColor));
    }

    @Override
    protected CanvasLayer<QtPainter, CanvasLayer<QtPainter, CanvasElement<QtPainter>>> createLayer(final int layer) {
        return new QtShapeLayer(layer, modelState);
    }
}
