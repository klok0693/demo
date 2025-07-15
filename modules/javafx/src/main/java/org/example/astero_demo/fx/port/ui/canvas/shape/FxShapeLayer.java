package org.example.astero_demo.fx.port.ui.canvas.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.core.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.core.port.ui.canvas.shape.EllipseElement;
import org.example.astero_demo.core.port.ui.canvas.shape.RectangleElement;
import org.example.astero_demo.core.port.ui.canvas.shape.ShapeElement;
import org.example.astero_demo.core.port.ui.canvas.shape.ShapeLayer;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasLayer;

public class FxShapeLayer extends ShapeLayer<GraphicsContext> {

    public FxShapeLayer(final ModelState modelState) {
        super(modelState);
    }

    public FxShapeLayer(final int layer, final ModelState modelState) {
        super(layer, modelState);
    }

    @Override
    protected EllipseElement<GraphicsContext> createEllipse(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color fillColor) {
        return new FxEllipseElement(layer, modelRelatedId, x, y, width, height, fillColor);
    }


    @Override
    protected RectangleElement<GraphicsContext> createRectangle(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color fillColor) {
        return new FxRectangleElement(layer, modelRelatedId, x, y, width, height, fillColor);
    }

    @Override
    protected CanvasLayer<GraphicsContext, CanvasLayer<GraphicsContext, CanvasElement<GraphicsContext>>> createLayer(final int layer) {
        return new FxShapeLayer(layer, modelState);//new FxCanvasLayer(layer);
    }

    /*    @Override
    protected CanvasLayer<GraphicsContext, CanvasElement<GraphicsContext>> createLayer(final int layer) {
        return new FxCanvasLayer(layer);
    }*/
}
