package org.example.demo.core.port.ui.canvas.shape;

import lombok.Getter;
import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.api.graphics.color.Color;
import org.example.demo.core.port.ui.canvas.CanvasElement;

/**
 * Represents an shape element that can be drawn on a canvas.<p>
 * <b>Note:</b> this class has a natural ordering that is inconsistent with equals.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public abstract class ShapeElement<E extends GraphicsPainter> extends CanvasElement<E>
        implements Comparable<ShapeElement<E>> {

    private final int modelRelatedId;
    private final int layer;
    protected Color fillColor;

    protected ShapeElement(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height,
            final Color color) {
        super(x, y, width, height);
        this.modelRelatedId = modelRelatedId;
        this.layer = layer;
        this.fillColor = color;
    }

    @Override
    public int compareTo(final ShapeElement o) {
        return Integer.compare(layer, o.getLayer());
    }
}
