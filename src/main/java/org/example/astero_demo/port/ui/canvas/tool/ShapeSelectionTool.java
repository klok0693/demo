package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.port.ui.canvas.CanvasElement;
import org.example.astero_demo.port.ui.canvas.CanvasView;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

public class ShapeSelectionTool extends CanvasTool implements CanvasClickable {
    private CanvasView view;

    public ShapeSelectionTool(final CanvasView canvasView) {
        super(-1, -1, -1, -1, 0);
        this.view = canvasView;
        this.isActive = false;
    }

/*    public ShapeSelectionTool(
            final double x,
            final double y,
            final double width,
            final double height) {
        super(x, y, width, height, 0);
    }*/

    public void update(
            final double x,
            final double y,
            final double width,
            final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);
        gc.strokeRect(x, y, width, height);
    }

    @Override
    public void destroyLinks() {}

    @Override
    public void onMousePressed(final double x, final double y) {
        final ShapeElement element = view.elementAt(x, y);
        if (element!= null) {
            this.x = element.getX();
            this.y = element.getY();
            this.width = element.getWidth();
            this.height = element.getHeight();
            this.isActive = true;
        }
        else {
            reset();
        }
    }
}
