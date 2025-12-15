package org.example.astero_demo.swing.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.core.port.ui.canvas.Drawable;

import java.awt.*;

/**
 * JavaFX's realization of {@link CanvasElement}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public interface SwingCanvasElement extends Drawable<Graphics> {


    default void save(Graphics gc) {
        //gc.save();
    };

    default void restore(Graphics gc) {
        //gc.restore();
    };
}
