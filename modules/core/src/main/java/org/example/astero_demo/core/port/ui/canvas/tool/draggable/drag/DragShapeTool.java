package org.example.astero_demo.core.port.ui.canvas.tool.draggable.drag;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.core.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.DraggableTool;

import static java.lang.Double.parseDouble;

/**
 * Represents a tool used for dragging shapes on a canvas.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class DragShapeTool<E> extends DraggableTool<E> {
    private final ModelState modelState;

    protected double offsetX;
    protected double offsetY;

    protected DragShapeTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        super(1, adapter, uiState);
        this.modelState = modelState;
        this.offsetX = -1;
        this.offsetY = -1;
    }

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        if (!isEnabled()) {
            return false;
        }

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
        this.offsetX = mouseX - parseDouble(element.getX());
        this.offsetY = mouseY - parseDouble(element.getY());

        onDragUpdate(element);

        setActive(true);
        return true;
    }

    protected abstract void onDragUpdate(Shape shape);

    @Override
    public void update(final double x, final double y) {
        this.x = x;
        this.y = y;
        setVisible(true);
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
    public void performOperation(final double[] toolValues) {
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
