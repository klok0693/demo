package org.example.astero_demo.port.ui.canvas.tool;

import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;

import java.util.Arrays;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.String.valueOf;
import static javafx.geometry.Pos.*;

public class ShapeSelectionTool extends CanvasTool implements CanvasClickable, CanvasDraggable {
    private static final double MINIMAL_SIDE = 1.0;
    private final CanvasAdapter adapter;
    private final ModelState modelState;
    private final UIState uiState;
    private final List<ContactPoint> contactPoints;

    public ShapeSelectionTool(final CanvasAdapter adapter, final ModelState holder, final UIState uiState) {
        super(-1, -1, -1, -1, 0);
        this.adapter = adapter;
        this.modelState = holder;
        this.uiState = uiState;
        this.isVisible = false;

        contactPoints = Arrays.asList(
                new ContactPoint(0, TOP_LEFT),
                new ContactPoint(0, TOP_CENTER),
                new ContactPoint(0, TOP_RIGHT),
                new ContactPoint(0, CENTER_RIGHT),
                new ContactPoint(0, BOTTOM_RIGHT),
                new ContactPoint(0, BOTTOM_CENTER),
                new ContactPoint(0, BOTTOM_LEFT),
                new ContactPoint(0, CENTER_LEFT)
        );
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);
        gc.strokeRect(x, y, width, height);

        contactPoints.forEach(point -> point.draw(gc));
    }

    @Override
    public double[] reset() {
        contactPoints.forEach(ContactPoint::reset);
        return super.reset();
    }

    public boolean selectElement(final int id) {
        final Shape s = modelState.getShape(id);
        update(
                parseDouble(s.getX()),
                parseDouble(s.getY()),
                parseDouble(s.getWidth()),
                parseDouble(s.getHeight()));
        return true;
    }

    @Override
    public void onMousePressed(final double mouseX, final double mouseY) {
        contactPoints.forEach(contact -> contact.onDragDetected(mouseX, mouseY));
        final double pointX, pointY, width, height;
        if (isVisible) {
            final Shape s = modelState.getShape(uiState.getSelectedShapeId());
            update(
                    parseDouble(s.getX()),
                    parseDouble(s.getY()),
                    parseDouble(s.getWidth()),
                    parseDouble(s.getHeight()));
            return;
        }
        Shape element = modelState.findTopShapeAt(mouseX, mouseY).orElse(null);
        if (element == null) {
            if (isInBounds(mouseX, mouseY)) {
                // never true
                return;
                //element = modelState.findTopShapeAt(mouseX, mouseY).orElse(null);

            }
            else {
                reset();
                return;
            }
        }
        else {
            pointX = parseDouble(element.getX());
            pointY = parseDouble(element.getY());
            width = parseDouble(element.getWidth());
            height = parseDouble(element.getHeight());
        }


        //if (element != null) {
            update(pointX, pointY, width, height);
/*        }
        else {
            reset();
        }*/
    }

/*    private double[] getPointVal() {
        for (final ContactPoint contactPoint : contactPoints) {
            if (contactPoint.isActive) {
                return new double[] {contactPoint.getPointX(), }
            }
        }
    }*/

    void update(final double x, final double y, final double width, final double height) {
        this.x = x ;//Math.max(x, 0);
        this.y = y; //Math.max(y, 0);
        this.width = Math.max(width, MINIMAL_SIDE);
        this.height = Math.max(height, MINIMAL_SIDE);

        contactPoints.get(0).update(this.x, this.y, this.width, this.height);
        contactPoints.get(1).update(this.x + (this.width / 2), this.y, this.width, this.height);
        contactPoints.get(2).update(this.x + this.width, this.y, this.width, this.height);
        contactPoints.get(3).update(this.x + this.width, this.y + (this.height / 2), this.width, this.height);
        contactPoints.get(4).update(this.x + this.width, this.y + this.height, this.width, this.height);
        contactPoints.get(5).update(this.x + (this.width / 2), this.y + this.height, this.width, this.height);
        contactPoints.get(6).update(this.x, this.y + this.height, this.width, this.height);
        contactPoints.get(7).update(this.x, this.y + (this.height / 2), this.width, this.height);

        this.isVisible = true;
    }

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        return contactPoints.stream().anyMatch(contact -> contact.onDragDetected(mouseX, mouseY));
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        contactPoints.stream()
                .filter(point -> point.isActive)
                .forEach(point -> point.onMouseDragged(mouseX, mouseY));
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

    private class ContactPoint extends DraggableTool implements CanvasClickable {
        private static final double DIAMETER = 16.0;
        private static final double RADIUS = DIAMETER / 2;

        private final Pos alignment;

        protected ContactPoint(final int layer, final Pos alignment) {
            super(DIAMETER, DIAMETER, layer);
            this.alignment = alignment;
            this.isVisible = false;
        }

        public void update(final double x, final double y, final double width, final double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.isVisible = true;
        }

        @Override
        protected void drawElement(final GraphicsContext gc) {
            gc.setFill(Color.RED);
            gc.fillOval(x - RADIUS, y - RADIUS, DIAMETER, DIAMETER);
        }

        public boolean isInBounds(final double x, final double y) {
            final double dx = x - this.x; // Distance from the point to the circle's center (x-axis)
            final double dy = y - this.y; // Distance from the point to the circle's center (y-axis)
            final double distanceSquared = dx * dx + dy * dy; // Square of the distance to the center
            final double radiusSquared = RADIUS * RADIUS; // Square of the radius

            return distanceSquared <= radiusSquared; // Check if the point is within the circle
        }

        @Override
        public void onMousePressed(final double x, final double y) {
/*            if (isInBounds(x, y)) {
                this.isVisible = true;
            }*/
        }

        @Override
        public boolean onDragDetected(final double mouseX, final double mouseY) {
            if (isVisible && isInBounds(mouseX, mouseY)) {
                this.isActive = true;
                ShapeSelectionTool.this.isVisible = true;
                return true;
            }
            return false;
        }

        @Override
        protected void update(double mouseX, double mouseY) {
            final double parentX = ShapeSelectionTool.this.x;
            final double parentY = ShapeSelectionTool.this.y;
            final double parentWidth = ShapeSelectionTool.this.width;
            final double parentHeight = ShapeSelectionTool.this.height;

            switch (alignment) {
                case TOP_LEFT -> {
                    final double newX = Math.min(mouseX, parentX + parentWidth - MINIMAL_SIDE);
                    final double newY = Math.min(mouseY, parentY + parentHeight - MINIMAL_SIDE);
                    final double newWidth = parentWidth - (mouseX - parentX);
                    final double newHeight = parentHeight - (mouseY - parentY);
                    ShapeSelectionTool.this.update(newX, newY, newWidth, newHeight);
                }
                case TOP_CENTER -> {
                    final double newY = Math.min(mouseY, parentY + parentHeight - MINIMAL_SIDE);
                    ShapeSelectionTool.this.update(parentX, newY, parentWidth, parentHeight - (mouseY - parentY));
                }
                case TOP_RIGHT -> {
                    final double newY = Math.min(parentY - (parentY - mouseY), parentY + parentHeight - MINIMAL_SIDE);
                    ShapeSelectionTool.this.update(parentX, newY, mouseX - parentX, (parentY + parentHeight) - mouseY);
                }
                case CENTER_RIGHT -> ShapeSelectionTool.this.update(parentX, parentY, mouseX - parentX, parentHeight);
                case BOTTOM_RIGHT -> ShapeSelectionTool.this.update(parentX, parentY, mouseX - parentX, mouseY - parentY);
                case BOTTOM_CENTER -> ShapeSelectionTool.this.update(parentX, parentY, parentWidth, mouseY - parentY);
                case BOTTOM_LEFT -> {
                    final double newX = Math.min(parentX - (parentX - mouseX), parentX + parentWidth - MINIMAL_SIDE);
                    final double newY = Math.min(parentY, parentY + parentHeight - MINIMAL_SIDE);
                    final double newWidth = (parentX + parentWidth) - mouseX;
                    final double newHeight = mouseY - parentY;
                    ShapeSelectionTool.this.update(newX, newY, newWidth, newHeight);
                }
                case CENTER_LEFT -> {
                    final double newX = Math.min(parentX - (parentX - mouseX), parentX + parentWidth - MINIMAL_SIDE);
                    ShapeSelectionTool.this.update(newX, parentY, (parentX + parentWidth) - mouseX, parentHeight);
                }
            }
        }

        @Override
        protected void performOperation(final double[] toolValues) {
            final double parentX = ShapeSelectionTool.this.x;
            final double parentY = ShapeSelectionTool.this.y;
            final double parentWidth = ShapeSelectionTool.this.width;
            final double parentHeight = ShapeSelectionTool.this.height;
            adapter.onDragOver(parentX, parentY, parentWidth, parentHeight);
        }
    }
}
