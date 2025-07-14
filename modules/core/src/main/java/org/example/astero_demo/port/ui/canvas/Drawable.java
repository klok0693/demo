package org.example.astero_demo.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents any element, that can be drawn on canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface Drawable {

    void draw(GraphicsContext gc);
}
