package org.example.astero_demo.port.ui.canvas.tool.draggable;

import javafx.scene.input.MouseEvent;

public interface CanvasDraggable {

    boolean onDragDetected(double mouseX, double mouseY);

    void onMouseDragged(double mouseX, double mouseY);

    void onMouseReleased(MouseEvent event);
}
