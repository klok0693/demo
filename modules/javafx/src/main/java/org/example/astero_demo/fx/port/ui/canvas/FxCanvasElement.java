package org.example.astero_demo.fx.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.core.port.ui.canvas.Drawable;

/**
 * JavaFX's realization of {@link CanvasElement}. {@link #save(GraphicsContext)}<p>
 * and {@link #restore(GraphicsContext)} methods necessary, because JavaFX's<p>
 * graphics context store the state of the rendered pixels
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public interface FxCanvasElement extends Drawable<GraphicsContext> {


    default void save(GraphicsContext gc) {
        gc.save();
    };

    default void restore(GraphicsContext gc) {
        gc.restore();
    };
}
