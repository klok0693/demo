package org.example.astero_demo.core.port.ui.canvas.tool;

public interface SelectionFrame<E extends Object> extends CanvasTool<E> {

    void update(double x, double y, double width, double height);

    boolean isInBounds(double x, double y);
}
