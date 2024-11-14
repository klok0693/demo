package org.example.astero_demo.port.ui.canvas.element;

import lombok.Getter;
import org.example.astero_demo.port.ui.canvas.CanvasElement;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;

public abstract class ShapeElement extends CanvasElement implements Comparable<ShapeElement> {
    @Getter
    private final int modelRelatedId;
    @Getter
    private int layer;

    protected ShapeElement(
            final int layer,
            final int modelRelatedId,
            final double x,
            final double y,
            final double width,
            final double height) {
        super(x, y, width, height);
        this.modelRelatedId = modelRelatedId;
        this.layer = layer;
    }

    public boolean isInBounds(final double x, final double y) {
        return x >= this.x && x <= (this.x + this.width)
                && y >= this.y && y <= (this.y + this.height);
    }

    @Override
    public int compareTo(final ShapeElement o) {
        return Integer.compare(this.layer, o.layer);
    }
}
