package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.tool.draggable.CanvasDraggable;
import org.example.astero_demo.port.ui.canvas.tool.draggable.ContactPoint;

import java.util.Arrays;
import java.util.List;

import static org.example.astero_demo.port.ui.UIConsrants.MINIMAL_SIDE_SIZE;
import static org.example.astero_demo.port.ui.canvas.tool.draggable.ContactAlignment.*;

public class SelectionFrame extends CanvasTool {
    private static final double FRAME_WIDTH = 3.0;
    protected final Color fillColor;

    public SelectionFrame() {
        super(-1, -1, -1, -1, 0);

        this.isVisible = false;
        this.fillColor = Color.RED;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setStroke(fillColor);
        gc.setLineWidth(FRAME_WIDTH);
        gc.strokeRect(x, y, width, height);
    }

    public void update(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = Math.max(width, MINIMAL_SIDE_SIZE);
        this.height = Math.max(height, MINIMAL_SIDE_SIZE);
        this.isVisible = true;
    }

    public boolean isInBounds(final double x, final double y) {
        return x >= this.x && x <= this.x + this.width
                && y >= this.y && y <= this.y + this.height;
    }

    public void makeVisible() {
        this.isVisible = true;
        //ShapeSelectionTool.this.makeVisible();
    }
}
