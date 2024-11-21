package org.example.astero_demo.port.ui.canvas.shape;

import javafx.scene.paint.Color;
import lombok.Getter;
import org.example.astero_demo.port.ui.canvas.CanvasElement;

/**
 * Represents an shape element that can be drawn on a canvas.<p>
 * <b>Note:</b> this class has a natural ordering that is inconsistent with equals.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public abstract class ShapeElement extends CanvasElement implements Comparable<ShapeElement> {
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
            final Color fillColor) {
        super(x, y, width, height);
        this.modelRelatedId = modelRelatedId;
        this.layer = layer;
        this.fillColor = fillColor;
    }

    @Override
    public int compareTo(final ShapeElement o) {
        return Integer.compare(this.layer, o.layer);
    }
}
