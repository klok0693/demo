package org.example.astero_demo.port.ui.canvas.tool.draggable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.state.ModelState;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.util.ColorUtils;

import static java.lang.Double.parseDouble;

/**
 * Represents a tool used for dragging shapes on a canvas.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class DragShapeTool extends DraggableTool {
    private final CanvasAdapter adapter;
    private final ModelState modelState;
    private final UIState uiState;

    private double offsetX;
    private double offsetY;

    public DragShapeTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        super(1);
        this.adapter = adapter;
        this.modelState = modelState;
        this.uiState = uiState;
        this.offsetX = -1;
        this.offsetY = -1;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(fillColor.darker());
        gc.setGlobalAlpha(OPACITY);

        switch (uiState.getSelectedShapeType()) {
            case RECT -> gc.fillRect(x - offsetX, y - offsetY, width, height);
            case ELLIPSE -> gc.fillOval(x - offsetX, y - offsetY, width, height);
        }
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        final double mouseX = event.getX();
        final double mouseY = event.getY();

        if (uiState.isMultipleSelection()) {
            return false;
        }

        final Shape element = uiState.hasSelectedId()
                ? modelState.getShape(uiState.getSelectedShapeId())
                : modelState.findTopShapeAt(mouseX, mouseY).orElse(null);

        if (element == null
                || !element.isInBounds(mouseX, mouseY)
                || !element.isInVisibleBounds(mouseX, mouseY)) {
            return false;
        }

        this.x = mouseX;
        this.y = mouseY;
        this.width = parseDouble(element.getWidth());
        this.height = parseDouble(element.getHeight());
        this.fillColor = ColorUtils.convert(element.getColor());
        this.offsetX = mouseX - parseDouble(element.getX());
        this.offsetY = mouseY - parseDouble(element.getY());
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
    public double[] reset() {
        final double[] values = new double[6];
        final double[] parentValues = super.reset();
        System.arraycopy(parentValues, 0, values, 0, 4);

        values[4] = this.offsetX;
        values[5] = this.offsetY;

        this.offsetX = -1;
        this.offsetY = -1;

        return values;
    }

    @Override
    protected void performOperation(final double[] toolValues) {
        if (!uiState.hasSelectedId()) {
            return;
        }
        final double dragX = toolValues[0];
        final double dragY = toolValues[1];
        final double offsetX = toolValues[4];
        final double offsetY = toolValues[5];
        adapter.moveSelectedShapeTo(dragX - offsetX, dragY - offsetY);
    }
}
