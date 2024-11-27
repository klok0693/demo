package org.example.astero_demo.port.ui.canvas.tool.draggable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.tool.CanvasClickable;
import org.example.astero_demo.port.ui.canvas.tool.SelectionFrame;

import java.util.Arrays;
import java.util.List;

import static org.example.astero_demo.port.ui.canvas.tool.draggable.ContactAlignment.*;
import static org.example.astero_demo.port.ui.canvas.tool.draggable.ContactAlignment.CENTER_LEFT;

public class ModificableSelectionFrame extends SelectionFrame implements CanvasClickable, CanvasDraggable {
    private final UIState uiState;
    private final List<ContactPoint> contactPoints;

    public ModificableSelectionFrame(final CanvasAdapter adapter, final UIState uiState) {
        super();
        this.uiState = uiState;
        this.contactPoints = Arrays.asList(
                new ContactPoint(this, adapter, 0, fillColor, TOP_LEFT),
                new ContactPoint(this, adapter, 0, fillColor, TOP_CENTER),
                new ContactPoint(this, adapter, 0, fillColor, TOP_RIGHT),
                new ContactPoint(this, adapter, 0, fillColor, CENTER_RIGHT),
                new ContactPoint(this, adapter, 0, fillColor, BOTTOM_RIGHT),
                new ContactPoint(this, adapter, 0, fillColor, BOTTOM_CENTER),
                new ContactPoint(this, adapter, 0, fillColor, BOTTOM_LEFT),
                new ContactPoint(this, adapter, 0, fillColor, CENTER_LEFT)
        );
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        super.drawElement(gc);
        contactPoints.forEach(point -> point.draw(gc));
    }

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
    public void onMousePressed(final MouseEvent event) {
        contactPoints.forEach(contact -> contact.onDragDetected(event));
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        return contactPoints.stream().anyMatch(contact -> contact.onDragDetected(event));
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        contactPoints.forEach(point -> point.onMouseDragged(mouseX, mouseY));
    }

    @Override
    public void onMouseReleased(final MouseEvent event) {
        contactPoints.forEach(contact -> contact.onMouseReleased(event));
    }

    @Override
    public boolean isInBounds(final double x, final double y) {
        final boolean isInShapeBounds = super.isInBounds(x, y);
        return isInShapeBounds ? isInShapeBounds : contactPoints.stream().anyMatch(contact -> contact.isInBounds(x, y));
    }
}
