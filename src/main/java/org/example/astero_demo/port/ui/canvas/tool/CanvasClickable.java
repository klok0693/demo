package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.input.MouseEvent;

@FunctionalInterface
public interface CanvasClickable {

    void onMousePressed(MouseEvent event);
}
