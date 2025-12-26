package org.example.demo.api.graphics;

/**
 * Represents any element, that can be drawn on canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface Drawable<E extends GraphicsPainter> extends Graphics<E> {

    void draw(E gc);
}
