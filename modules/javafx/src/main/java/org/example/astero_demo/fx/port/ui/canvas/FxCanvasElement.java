package org.example.astero_demo.fx.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.api.graphics.Drawable;
import org.example.astero_demo.fx.port.ui.graphics.FxPainter;

/**
 * JavaFX's realization of {@link CanvasElement}. {@link #save(GraphicsContext)}<p>
 * and {@link #restore(GraphicsContext)} methods necessary, because JavaFX's<p>
 * graphics context store the state of the rendered pixels
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public interface FxCanvasElement extends Drawable<FxPainter> {

    default void save(final FxPainter gc) {
        gc.save();
    };

    default void restore(final FxPainter gc) {
        gc.restore();
    };
}
