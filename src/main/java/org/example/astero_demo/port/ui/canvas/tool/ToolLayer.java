package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;

import java.util.Optional;

public class ToolLayer extends CanvasLayer<CanvasTool> {

    public ToolLayer(GraphicsContext gc) {
        super(gc, 2);
    }

    public void createDragTool(
            final double x,
            final double y,
            final double width,
            final double height,
            final Color color,
            final double xOffset,
            final double yOffset) {

        add(new DragShapeTool(x, y, width, height, color, xOffset, yOffset));
    }

    public void destroyDragTool() {
        getDragTool().ifPresent(this::remove);
    }

    public Optional<DragShapeTool> getDragTool() {
        return getChildren()
                .filter(tool -> tool instanceof DragShapeTool)
                .map(DragShapeTool.class::cast)
                .findFirst();
    }
}
