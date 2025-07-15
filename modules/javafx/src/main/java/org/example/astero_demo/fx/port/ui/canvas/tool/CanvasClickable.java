package org.example.astero_demo.fx.port.ui.canvas.tool;

import javafx.scene.input.MouseEvent;

/**
 * Element, receiving a mouse press event from the canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface CanvasClickable {

    void onMousePressed(MouseEvent event);
}
