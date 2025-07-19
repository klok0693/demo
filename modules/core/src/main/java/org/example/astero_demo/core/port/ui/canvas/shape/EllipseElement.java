package org.example.astero_demo.core.port.ui.canvas.shape;

/**
 * Ellipse element
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class EllipseElement<E> extends ShapeElement<E> {

    protected EllipseElement(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height) {
        super(layer, modelRelatedId, x, y, width, height);
    }
}
