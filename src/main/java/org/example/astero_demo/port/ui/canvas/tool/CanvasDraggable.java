package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.input.MouseEvent;

public interface CanvasDraggable {

    void onDragDetected(MouseEvent event);

    void onMouseDragged(MouseEvent event);

    void onMouseReleased(MouseEvent event);
}
