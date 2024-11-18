package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.util.ColorUtils;

import static java.lang.Double.parseDouble;

public class DragShapeTool extends DraggableTool {
    private final CanvasAdapter adapter;
    private final ModelState modelState;
    private final UIState uiState;

    private Color color;
    private double xOffset;
    private double yOffset;

    public DragShapeTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        super(1);
        this.adapter = adapter;
        this.modelState = modelState;
        this.uiState = uiState;
        this.color = null;
        this.xOffset = -1;
        this.yOffset = -1;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(color);
        gc.setGlobalAlpha(OPACITY);

        switch (uiState.getSelectedShapeType()) {
            case RECT:
                gc.fillRect(x - xOffset, y - yOffset, width, height);
                break;
            case OVAL:
                gc.fillOval(x - xOffset, y - yOffset, width, height);
                break;
        }
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        final double mouseX = event.getX();
        final double mouseY = event.getY();

        final Shape element =  modelState.findShapeAt(mouseX, mouseY).findFirst().orElse(null);
        if (element == null || !element.isInBounds(mouseX, mouseY)) {
            return false;
        }

        this.x = mouseX;
        this.y = mouseY;
        this.width = parseDouble(element.getWidth());
        this.height = parseDouble(element.getHeight());
        this.color = ColorUtils.convert(element.getColor());
        this.xOffset = mouseX - parseDouble(element.getX());
        this.yOffset = mouseY - parseDouble(element.getY());
        this.isActive = true;
        return true;
    }

    @Override
    protected void update(final double x, final double y) {
        this.x = x;
        this.y = y;
        this.isVisible = true;
    }

    @Override
    public void onMouseReleased(final MouseEvent event, final boolean isOnBounds) {
        this.isActive = false;
        if (!isVisible) {
            return;
        }

        final double[] dragPosition = new double[] {x - xOffset, y - yOffset};
        reset();
        if (uiState.hasSelectedId() && isOnBounds) {
            adapter.onDragOver(dragPosition[0], dragPosition[1]);
        }
    }
}
