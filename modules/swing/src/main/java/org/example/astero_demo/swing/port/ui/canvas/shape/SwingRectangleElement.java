package org.example.astero_demo.swing.port.ui.canvas.shape;

import org.example.astero_demo.core.port.ui.canvas.shape.RectangleElement;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

import java.awt.*;

/**
 * JavaFX's realization of {@link RectangleElement}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingRectangleElement extends RectangleElement<SwingPainter> implements SwingCanvasElement {
    //TODO: Someday, sometime
    private double opacity;
    private double scale;
    private double angle;
    private double pivotX, pivotY;

    public SwingRectangleElement(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color fillColor) {
        super(layer, modelRelatedId, x, y, width, height,
                org.example.astero_demo.api.graphics.color.Color.rgb(
                        fillColor.getRed(),
                        fillColor.getGreen(),
                        fillColor.getBlue()
                ));
    }
}
