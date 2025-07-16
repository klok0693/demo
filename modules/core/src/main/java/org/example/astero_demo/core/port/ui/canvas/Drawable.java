package org.example.astero_demo.core.port.ui.canvas;

/**
 * Represents any element, that can be drawn on canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface Drawable<T extends Object> {

    void draw(T gc);
}
