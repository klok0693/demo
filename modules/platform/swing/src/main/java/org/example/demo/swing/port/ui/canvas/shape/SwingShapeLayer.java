package org.example.demo.swing.port.ui.canvas.shape;

import org.example.demo.api.graphics.color.Colors;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.CanvasElement;
import org.example.demo.core.port.ui.canvas.CanvasLayer;
import org.example.demo.core.port.ui.canvas.shape.EllipseElement;
import org.example.demo.core.port.ui.canvas.shape.RectangleElement;
import org.example.demo.core.port.ui.canvas.shape.ShapeLayer;
import org.example.demo.swing.port.ui.graphics.SwingPainter;

/**
 * Swing realization of {@link ShapeLayer}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingShapeLayer extends ShapeLayer<SwingPainter> {

    public SwingShapeLayer(final ModelState modelState) {
        super(modelState);
    }

    public SwingShapeLayer(final int layer, final ModelState modelState) {
        super(layer, modelState);
    }

    @Override
    protected EllipseElement<SwingPainter> createEllipse(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final String fillColor) {
        return new SwingEllipseElement(layer, modelRelatedId, x, y, width, height, Colors.convert(fillColor));
    }


    @Override
    protected RectangleElement<SwingPainter> createRectangle(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final String fillColor) {
        return new SwingRectangleElement(layer, modelRelatedId, x, y, width, height, Colors.convert(fillColor));
    }

    @Override
    protected CanvasLayer<SwingPainter, CanvasLayer<SwingPainter, CanvasElement<SwingPainter>>> createLayer(final int layer) {
        return new SwingShapeLayer(layer, modelState);
    }
}
