package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;

public class ToolLayer extends CanvasLayer<ShapeSelectionTool> {

    public ToolLayer(GraphicsContext gc) {
        super(gc, 2);
    }
}
