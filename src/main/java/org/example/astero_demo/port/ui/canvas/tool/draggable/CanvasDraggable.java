package org.example.astero_demo.port.ui.canvas.tool.draggable;

import javafx.scene.input.MouseEvent;

/**
 * Element, receiving a drag events from the canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CanvasDraggable {

    boolean onDragDetected(MouseEvent event);

    void onMouseDragged(double mouseX, double mouseY);

    void onMouseReleased(MouseEvent event);
}
