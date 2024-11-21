package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.tool.draggable.CanvasDraggable;
import org.example.astero_demo.port.ui.canvas.tool.draggable.ContactPoint;

import java.util.Arrays;
import java.util.List;

import static java.lang.Double.parseDouble;
import static org.example.astero_demo.port.ui.UIConsrants.MINIMAL_SIDE_SIZE;
import static org.example.astero_demo.port.ui.canvas.tool.draggable.ContactAlignment.*;

public class ShapeSelectionTool extends CanvasTool implements CanvasClickable, CanvasDraggable, UpdatableView {
    private static final double FRAME_WIDTH = 3.0;

    private final ModelState modelState;
    private final UIState uiState;
    private final List<ContactPoint> contactPoints;

    private final Color fillColor;

    public ShapeSelectionTool(final CanvasAdapter adapter, final ModelState holder, final UIState uiState) {
        super(-1, -1, -1, -1, 0);
        this.modelState = holder;
        this.uiState = uiState;
        this.isVisible = false;
        this.fillColor = Color.RED;

        contactPoints = Arrays.asList(
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
        gc.setStroke(fillColor);
        gc.setLineWidth(FRAME_WIDTH);
        gc.strokeRect(x, y, width, height);

        contactPoints.forEach(point -> point.draw(gc));
    }

    @Override
    public void update() {
        reset();

        if (uiState.hasSelectedId()) {
            final Shape selection = modelState.getShape(uiState.getSelectedShapeId());
            update(
                    parseDouble(selection.getX()),
                    parseDouble(selection.getY()),
                    parseDouble(selection.getWidth()),
                    parseDouble(selection.getHeight())
            );
        }
    }

    @Override
    public double[] reset() {
        contactPoints.forEach(ContactPoint::reset);
        return super.reset();
    }

    public boolean selectElement(final int id) {
        final Shape selection = modelState.getShape(id);
        update(
                parseDouble(selection.getX()),
                parseDouble(selection.getY()),
                parseDouble(selection.getWidth()),
                parseDouble(selection.getHeight()));
        return true;
    }

    @Override
    public void onMousePressed(final double mouseX, final double mouseY) {
        contactPoints.forEach(contact -> contact.onDragDetected(mouseX, mouseY));

        if (isVisible && uiState.hasSelectedId()) {
            final Shape selection = modelState.getShape(uiState.getSelectedShapeId());
            update(
                    parseDouble(selection.getX()),
                    parseDouble(selection.getY()),
                    parseDouble(selection.getWidth()),
                    parseDouble(selection.getHeight())
            );
            return;
        }

        modelState.findTopShapeAt(mouseX, mouseY)
                .ifPresentOrElse(element -> update(
                        parseDouble(element.getX()),
                        parseDouble(element.getY()),
                        parseDouble(element.getWidth()),
                        parseDouble(element.getHeight())
                ), this::reset);
    }

    public void update(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = Math.max(width, MINIMAL_SIDE_SIZE);
        this.height = Math.max(height, MINIMAL_SIDE_SIZE);

        contactPoints.forEach(contact -> contact.update(this.x, this.y, this.width, this.height));
        this.isVisible = true;
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
    public void onMouseReleased(final MouseEvent event) {
        contactPoints.forEach(contact -> contact.onMouseReleased(event));
    }

    public boolean isInBounds(final double x, final double y) {
        final boolean isInShapeBounds =
                x >= this.x && x <= this.x + this.width
                        && y >= this.y && y <= this.y + this.height;

        return isInShapeBounds ? isInShapeBounds : contactPoints.stream().anyMatch(contact -> contact.isInBounds(x, y));
    }

    public void makeVisible() {
        this.isVisible = true;
    }
}
