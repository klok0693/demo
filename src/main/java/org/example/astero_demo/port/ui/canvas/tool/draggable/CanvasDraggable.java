package org.example.astero_demo.port.ui.canvas.tool.draggable;

import javafx.scene.input.MouseEvent;

public interface CanvasDraggable {

    boolean onDragDetected(MouseEvent event);

    void onMouseDragged(double mouseX, double mouseY);

    void onMouseReleased(MouseEvent event);
}
