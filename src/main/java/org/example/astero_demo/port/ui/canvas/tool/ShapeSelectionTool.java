package org.example.astero_demo.port.ui.canvas.tool;

import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.port.ui.canvas.CanvasView;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

import java.util.Arrays;
import java.util.List;

import static javafx.geometry.Pos.*;

public class ShapeSelectionTool extends CanvasTool implements CanvasClickable, CanvasDraggable {
    private CanvasView view;
    private final List<ContactPoint> contactPoints;

    public ShapeSelectionTool(final CanvasView canvasView, final int layer) {
        super(-1, -1, -1, -1, layer);
        this.view = canvasView;
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
    public void reset() {
        super.reset();
        contactPoints.forEach(ContactPoint::reset);
    }

    @Override
    public void destroyLinks() {}

    @Override
    public void onMousePressed(final double x, final double y) {
        final ShapeElement element = view.elementAt(x, y);
        if (element!= null) {
            update(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        }
        else {
            reset();
        }
    }

    void update(final double x, final double y, final double width, final double height) {
        this.x = Math.max(x, 0);
        this.y = Math.max(y, 0);
        this.width = Math.max(width, 1);
        this.height = Math.max(height, 1);

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
    public boolean onDragDetected(final MouseEvent event) {
        return contactPoints.stream().anyMatch(contact -> contact.onDragDetected(event));
    }

    @Override
    public void onMouseDragged(final MouseEvent event) {
        contactPoints.stream()
                .filter(point -> point.isSelected)
                .forEach(point -> point.onMouseDragged(event));
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

    private class ContactPoint extends CanvasTool implements CanvasDraggable {
        private static final double RADIUS = 16.0;

        private final Pos alignment;
        private boolean isSelected;

        protected ContactPoint(final int layer, final Pos alignment) {
            super(-1, -1, RADIUS, RADIUS, layer);
            this.alignment = alignment;
            this.isVisible = true;
            this.isSelected = false;
        }

/*        public void update(final double x, final double y) {
            update(x, y, this.width, this.height);
        }*/

        public void update(final double x, final double y, final double width, final double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        protected void drawElement(final GraphicsContext gc) {
            gc.setFill(Color.RED);
            gc.fillOval(x - (RADIUS / 2), y - (RADIUS / 2), RADIUS, RADIUS);
        }

        public boolean isInBounds(final double x, final double y) {
            return x >= (this.x - (RADIUS / 2)) && x <= (this.x + (RADIUS / 2))
                    && y >= (this.y - (RADIUS / 2)) && y <= (this.y + (RADIUS / 2));
        }

        @Override
        public void reset() {
            this.isSelected = false;
        }

        @Override
        public void destroyLinks() {

        }

        @Override
        public boolean onDragDetected(final MouseEvent event) {
            if (isVisible && isInBounds(event.getX(), event.getY())) {
                this.isSelected = true;
                ShapeSelectionTool.this.isVisible = true;
                return true;
            }
            return false;
        }

        @Override
        public void onMouseDragged(MouseEvent event) {
            if (!isSelected) {
                return;
            }
            final double mouseX = event.getX();
            final double mouseY = event.getY();

            final double parentX = ShapeSelectionTool.this.x;
            final double parentY = ShapeSelectionTool.this.y;
            final double parentWidth = ShapeSelectionTool.this.width;
            final double parentHeight = ShapeSelectionTool.this.height;

            switch (alignment) {
                case TOP_LEFT -> ShapeSelectionTool.this.update(mouseX, mouseY, parentWidth - (mouseX - parentX), parentHeight - (mouseY - parentY));
                case TOP_CENTER -> ShapeSelectionTool.this.update(parentX, mouseY, parentWidth, parentHeight - (mouseY - parentY));
                case TOP_RIGHT -> ShapeSelectionTool.this.update(parentX, parentY - (parentY - mouseY), mouseX - parentX, (parentY + parentHeight) - mouseY);
                case CENTER_RIGHT -> ShapeSelectionTool.this.update(parentX, parentY, mouseX - parentX, parentHeight);
                case BOTTOM_RIGHT -> ShapeSelectionTool.this.update(parentX, parentY, mouseX - parentX, mouseY - parentY);
                case BOTTOM_CENTER -> ShapeSelectionTool.this.update(parentX, parentY, parentWidth, mouseY - parentY);
                case BOTTOM_LEFT -> ShapeSelectionTool.this.update(parentX - (parentX - mouseX), parentY, (parentX + parentWidth) - mouseX, mouseY - parentY);
                case CENTER_LEFT -> ShapeSelectionTool.this.update(parentX - (parentX - mouseX), parentY, (parentX + parentWidth) - mouseX, parentHeight);
            }
        }

        @Override
        public void onMouseReleased(MouseEvent event) {
            if (!isSelected) {
                return;
            }

            final double parentX = ShapeSelectionTool.this.x;
            final double parentY = ShapeSelectionTool.this.y;
            final double parentWidth = ShapeSelectionTool.this.width;
            final double parentHeight = ShapeSelectionTool.this.height;

            reset();
            view.onDragOver(parentX, parentY, parentWidth, parentHeight);
        }
    }
}
