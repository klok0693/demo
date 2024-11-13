package org.example.astero_demo.port.ui.canvas.element;

import org.example.astero_demo.port.ui.canvas.CanvasElement;

public abstract class ShapeElement extends CanvasElement {

    protected ShapeElement(
            final double x,
            final double y,
            final double width,
            final double height) {
        super(x, y, width, height);
    }

    public boolean isInBounds(final double x, final double y) {
        return x >= this.x && x <= (this.x + this.width)
                && y >= this.y && y <= (this.y + this.height);
    }
}
