package org.example.astero_demo.port.ui.canvas.shape;

import javafx.scene.paint.Color;
import lombok.Getter;
import org.example.astero_demo.port.ui.canvas.CanvasElement;

public abstract class ShapeElement extends CanvasElement implements Comparable<ShapeElement> {
    @Getter
    private final int modelRelatedId;
    @Getter
    private final int layer;
    @Getter
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
