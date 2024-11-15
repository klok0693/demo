package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lombok.Setter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.CanvasElement;
import org.example.astero_demo.port.ui.canvas.CanvasView;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;
import org.example.astero_demo.util.ColorUtils;

import java.util.Arrays;
import java.util.Optional;

public class DragShapeTool extends DraggableTool {
    private static final double OPACITY = 0.4;

    private final CanvasView canvasView;
    @Setter
    private UIState uiState;

    private Color color;

    private double xOffset;
    private double yOffset;

    public DragShapeTool(final CanvasView canvasView) {
        super(-1, -1, -1, -1, 1);
        this.color = null;
        this.xOffset = -1;
        this.yOffset = -1;
        this.canvasView = canvasView;
    }

    public DragShapeTool(
            final double x,
            final double y,
            final double width,
            final double height,
            final Color color,
            final double xOffset,
            final double yOffset) {
        super(x, y, width, height, 1);
        this.color = color;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.canvasView = null;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(color);
        gc.setGlobalAlpha(OPACITY);
        gc.fillRect(x - xOffset, y - yOffset, width, height);
    }

    @Override
    public void destroyLinks() {

    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        final double mouseX = event.getX();
        final double mouseY = event.getY();

        final ShapeElement element = canvasView.elementAt(mouseX, mouseY);
        if (element == null || !element.isInBounds(mouseX, mouseY)) {
            // TODO: always false?
            return false;
        }

        this.x = mouseX;
        this.y = mouseY;
        this.width = element.getWidth();
        this.height = element.getHeight();
        this.color = element.getFillColor();
        this.xOffset = mouseX - element.getX();
        this.yOffset = mouseY - element.getY();
        this.isActive = true;

        return true;
    }

    @Override
    public void onMouseDragged(final MouseEvent event) {
        if (!isActive) {
            return;
        }
        final double mouseX = event.getX();
        final double mouseY = event.getY();

        final double endX = canvasView.getLayoutX() + canvasView.getWidth();
        final double endY = canvasView.getLayoutY() + canvasView.getHeight();

        this.x = Math.min(Math.max(0, mouseX), endX);
        this.y = Math.min(Math.max(0, mouseY), endY);
    }

    @Override
    public void onMouseReleased(final MouseEvent event) {
        if (!isActive) {
            return;
        }
        final double[] dragPosition = new double[] {x - xOffset, y - yOffset};
        //reset();

        if (uiState.hasSelectedId() && canvasView.getLayoutBounds().contains(event.getX(), event.getY())) {
            canvasView.onDragOver(dragPosition[0], dragPosition[1]);
        }
    }
}
