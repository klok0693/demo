package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
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
        this.color = null;
        this.offsetX = -1;
        this.offsetY = -1;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(color.darker());
        gc.setGlobalAlpha(OPACITY);

        switch (uiState.getSelectedShapeType()) {
            case RECT:
                gc.fillRect(x - offsetX, y - offsetY, width, height);
                break;
            case OVAL:
                gc.fillOval(x - offsetX, y - offsetY, width, height);
                break;
        }
    }

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        Shape element = null;
        if (uiState.hasSelectedId()) {
            Shape selected = modelState.getShape(uiState.getSelectedShapeId());
            if (selected.isInBounds(mouseX, mouseY)) {
                element = selected;
            }
        }
        else {
            element =  modelState.findTopShapeAt(mouseX, mouseY).orElse(null);
        }
        if (element == null || !element.isInVisibleBounds(mouseX, mouseY)) {
            return false;
        }

        this.x = mouseX;
        this.y = mouseY;
        this.width = parseDouble(element.getWidth());
        this.height = parseDouble(element.getHeight());
        this.color = ColorUtils.convert(element.getColor());
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
        adapter.onDragOver(dragX - offsetX, dragY - offsetY);
    }
}
