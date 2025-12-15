package org.example.astero_demo.swing.port.ui.canvas.shape;

import org.example.astero_demo.core.port.ui.canvas.shape.RectangleElement;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;

import java.awt.*;

/**
 * JavaFX's realization of {@link RectangleElement}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingRectangleElement extends RectangleElement<Graphics> implements SwingCanvasElement {
    //TODO: Someday, sometime
    private double opacity;
    private double scale;
    private double angle;
    private double pivotX, pivotY;

    protected Color fillColor;

    public SwingRectangleElement(
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
    protected void drawElement(final Graphics gc) {
/*        gc.setFill(fillColor);
        gc.fillRect(x, y, width, height);*/
    }

    @Override
    public void save(final Graphics gc) {
        SwingCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final Graphics gc) {
        SwingCanvasElement.super.restore(gc);
    }
}
