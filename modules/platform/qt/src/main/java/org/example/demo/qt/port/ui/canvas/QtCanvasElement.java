package org.example.demo.qt.port.ui.canvas;

import org.example.demo.core.port.ui.canvas.CanvasElement;
import org.example.demo.api.graphics.Drawable;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * JavaFX's realization of {@link CanvasElement}. {@link #save(GraphicsContext)}<p>
 * and {@link #restore(GraphicsContext)} methods necessary, because JavaFX's<p>
 * graphics context store the state of the rendered pixels
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public interface QtCanvasElement extends Drawable<QtPainter> {

    default void save(final QtPainter gc) {
        //gc.save();
    };

    default void restore(final QtPainter gc) {
        //gc.restore();
    };
}
