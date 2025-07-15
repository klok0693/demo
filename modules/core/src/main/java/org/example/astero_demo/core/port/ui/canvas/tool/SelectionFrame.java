package org.example.astero_demo.core.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static org.example.astero_demo.core.port.ui.UIConsrants.MINIMAL_SIDE_SIZE;

public interface SelectionFrame<E extends Object> extends CanvasTool<E> {

    void update(final double x, final double y, final double width, final double height);

    boolean isInBounds(final double x, final double y);
}
