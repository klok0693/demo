package org.example.astero_demo.core.port.ui.canvas;

/**
 * Represents an abstract element, that can be drawn on the canvas.<p>
 * Unlike layers, cannot be nested within each other, being leaves<p>
 * in the tree of elements on the canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CanvasElement<T extends Object> extends Drawable<T> {

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    double getWidth();

    void setWidth(double width);

    double getHeight();

    void setHeight(double height);
}
