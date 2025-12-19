package org.example.astero_demo.api.graphics;

/**
 * Provide painter for components with a custom ui
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface GraphicsContext<E extends GraphicsPainter> extends Graphics<E> {

    E getGraphicsPainter();
}
