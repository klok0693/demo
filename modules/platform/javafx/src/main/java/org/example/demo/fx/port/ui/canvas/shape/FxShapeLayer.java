package org.example.demo.fx.port.ui.canvas.shape;

import org.example.demo.api.graphics.color.Colors;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.CanvasElement;
import org.example.demo.core.port.ui.canvas.CanvasLayer;
import org.example.demo.core.port.ui.canvas.shape.EllipseElement;
import org.example.demo.core.port.ui.canvas.shape.RectangleElement;
import org.example.demo.core.port.ui.canvas.shape.ShapeLayer;
import org.example.demo.fx.port.ui.graphics.FxPainter;

/**
 * JavaFX's realization of {@link ShapeLayer}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxShapeLayer extends ShapeLayer<FxPainter> {

    public FxShapeLayer(final ModelState modelState) {
        super(modelState);
    }

    public FxShapeLayer(final int layer, final ModelState modelState) {
        super(layer, modelState);
    }

    @Override
    protected EllipseElement<FxPainter> createEllipse(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final String fillColor) {
        return new FxEllipseElement(layer, modelRelatedId, x, y, width, height, Colors.convert(fillColor));
    }


    @Override
    protected RectangleElement<FxPainter> createRectangle(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final String fillColor) {
        return new FxRectangleElement(layer, modelRelatedId, x, y, width, height, Colors.convert(fillColor));
    }

    @Override
    protected CanvasLayer<FxPainter, CanvasLayer<FxPainter, CanvasElement<FxPainter>>> createLayer(final int layer) {
        return new FxShapeLayer(layer, modelState);
    }
}
