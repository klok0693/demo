package org.example.astero_demo.swing.port.ui.canvas.shape;

import org.example.astero_demo.core.port.ui.canvas.shape.EllipseElement;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

import java.awt.*;

/**
 * JavaFX's realization of {@link EllipseElement}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingEllipseElement extends EllipseElement<SwingPainter> implements SwingCanvasElement {

    protected SwingEllipseElement(
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
