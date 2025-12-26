package org.example.demo.core.port.ui.canvas.tool.draggable.selection;

import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.api.graphics.color.Color;
import org.example.demo.api.graphics.color.Colors;
import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.CanvasClickable;
import org.example.demo.core.port.ui.canvas.tool.CanvasDraggable;

import java.util.Arrays;
import java.util.List;

import static org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment.*;
import static org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment.CENTER_LEFT;

public abstract class ModificableSelectionFrame<E extends GraphicsPainter> extends SelectionFrame<E>
        implements CanvasDraggable, CanvasClickable {
    private static final double FRAME_WIDTH = 3.0;

    protected final UIState uiState;
    protected final List<ContactPoint<E>> contactPoints;

    protected final Color fillColor;

    protected ModificableSelectionFrame(final CanvasAdapter adapter, final UIState uiState) {
        super();
        this.uiState = uiState;
        this.fillColor = Colors.RED;
        this.contactPoints = Arrays.asList(
                createPoint(adapter, 0, TOP_LEFT),
                createPoint(adapter, 0, TOP_CENTER),
                createPoint(adapter, 0, TOP_RIGHT),
                createPoint(adapter, 0, CENTER_RIGHT),
                createPoint(adapter, 0, BOTTOM_RIGHT),
                createPoint(adapter, 0, BOTTOM_CENTER),
                createPoint(adapter, 0, BOTTOM_LEFT),
                createPoint(adapter, 0, CENTER_LEFT)
        );
        setEnabled(true);
    }

    @Override
    protected void drawElement(final E gc) {
        gc.setStroke(fillColor);
        gc.setLineWidth(FRAME_WIDTH);
        gc.strokeRect(x, y, width, height);

        contactPoints.forEach(point -> point.draw(gc));
    }

    protected abstract ContactPoint<E> createPoint(
            CanvasAdapter adapter,
            int layer,
            ContactAlignment alignment
    );

    @Override
    public void update(final double x, final double y, final double width, final double height) {
        super.update(x, y, width, height);
        if (!uiState.isMultipleSelection()) {
            contactPoints.forEach(contact -> contact.update(this.x, this.y, this.width, this.height));
        }
    }

    @Override
    public double[] reset() {
        contactPoints.forEach(ContactPoint::reset);
        return super.reset();
    }

    @Override
    public void onMousePressed(final double mouseX, final double mouseY, final boolean isShiftDown) {
        contactPoints.forEach(contact -> contact.onDragDetected(mouseX, mouseY));
    }

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        return contactPoints.stream().anyMatch(contact -> contact.onDragDetected(mouseX, mouseY));
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        contactPoints.forEach(point -> point.onMouseDragged(mouseX, mouseY));
    }

    @Override
    public void onMouseReleased(final double mouseX, final double mouseY) {
        contactPoints.forEach(contact -> contact.onMouseReleased(mouseX, mouseY));
    }

    @Override
    public boolean isInBounds(double x, double y) {
        return super.isInBounds(x, y) || contactPoints.stream().anyMatch(point -> point.isInBounds(x, y));
    }
}
