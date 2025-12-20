package org.example.astero_demo.core.port.ui.canvas;

import org.example.astero_demo.api.graphics.GraphicsPainter;

import java.util.List;

/**
 * Component of {@link ShapeCanvasView}, used only
 * to a render canvas {@link org.example.astero_demo.api.graphics.Drawable elements}
 * <p>
 * Existed to separate rendering from other ui component's
 * functions, like event handling, that stays in {@link ShapeCanvasView CanvasView}
 *<p>
 * As Java does not support multiple state inheritance,
 * this interfaced must be inherited by canvas's realizations
 * to provide at least multiple behaviour inheritance
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public interface CanvasUI {

    /**
     * Java UI Systems are not frame-driven, so canvas
     * required direct command to redraw if state of
     * underlying components changed
     */
    void redraw();
}
