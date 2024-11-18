package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.input.MouseEvent;

public interface CanvasDraggable {

    boolean onDragDetected(MouseEvent event);

    void onMouseDragged(double x, double y);

    void onMouseReleased(MouseEvent event, boolean isOnBounds);
}
