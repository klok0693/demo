package org.example.astero_demo.fx.port.ui.canvas.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.port.ui.canvas.shape.EllipseElement;

public class FxEllipseElement extends FxShapeElement implements EllipseElement<GraphicsContext> {

    protected FxEllipseElement(
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
    protected void drawElement(GraphicsContext gc) {
        gc.setFill(fillColor);
        gc.fillOval(x, y, width, height);
    }
}
