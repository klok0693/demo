package org.example.astero_demo.core.port.ui.canvas.tool;

/**
 * Element, receiving a mouse press event from the canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface CanvasClickable {

    void onMousePressed(double mouseX, double mouseY, boolean isShiftDown);
}
