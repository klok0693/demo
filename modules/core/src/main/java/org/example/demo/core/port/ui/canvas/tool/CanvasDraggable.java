package org.example.demo.core.port.ui.canvas.tool;

/**
 * Element, receiving a drag events from the canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CanvasDraggable {

    boolean onDragDetected(double mouseX, double mouseY);

    void onMouseDragged(double mouseX, double mouseY);

    void onMouseReleased(double mouseX, double mouseY);
}
