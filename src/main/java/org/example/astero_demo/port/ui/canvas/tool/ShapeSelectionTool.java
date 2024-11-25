package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.state.ModelState;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.tool.draggable.CanvasDraggable;
import org.example.astero_demo.port.ui.canvas.tool.draggable.ContactPoint;

import java.util.*;

import static java.lang.Double.parseDouble;
import static org.example.astero_demo.port.ui.UIConsrants.MINIMAL_SIDE_SIZE;
import static org.example.astero_demo.port.ui.canvas.tool.draggable.ContactAlignment.*;

/**
 * Canvas tool, allowing to select shape element on a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ShapeSelectionTool extends CanvasTool implements CanvasClickable, CanvasDraggable, UpdatableView {
    private final ModelState modelState;
    private final UIState uiState;
    private final CanvasAdapter adapter;

    private final Map<Integer, SelectionFrame> frames = new HashMap<>(2);

    public ShapeSelectionTool(final CanvasAdapter adapter, final ModelState holder, final UIState uiState) {
        super(-1, -1, -1, -1, 0);
        this.modelState = holder;
        this.uiState = uiState;
        this.adapter = adapter;
        this.isVisible = false;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        frames.values().forEach(frame -> frame.draw(gc));
    }

    @Override
    public void update() {
        reset();

        if (uiState.hasSelectedId()) {
            uiState.getSelectedIds().forEach(id -> {
                final Shape selection = modelState.getShape(id);
                final SelectionFrame frame = new SelectionFrame(ShapeSelectionTool.this, adapter, uiState);

                final double frameX = parseDouble(selection.getX());
                final double frameY = parseDouble(selection.getY());
                final double frameWidth = parseDouble(selection.getWidth());
                final double frameHeight = parseDouble(selection.getHeight());
                frame.update(frameX, frameY, frameWidth, frameHeight);
                frames.put(id, frame);

                ShapeSelectionTool.this.isVisible = true;
            });
        }
    }

    @Override
    public double[] reset() {
        frames.values().forEach(SelectionFrame::reset);
        frames.clear();
        return super.reset();
    }

    @Override
    public void onMousePressed(final MouseEvent event) {
        final double mouseX = event.getX();
        final double mouseY = event.getY();

/*        if (event.isControlDown() && uiState.hasSelectedId()) {
            adapter.selectMultiple(mouseX, mouseY);
            return;
        }*/

        if (event.isShiftDown() && uiState.hasSelectedId()) {
            adapter.selectNextShapeAt(mouseX, mouseY);
            return;
        }

        frames.values().forEach(frame -> frame.onMousePressed(event));
        if (isVisible && uiState.hasSelectedId()) {
            uiState.getSelectedIds().forEach(id -> {
                final SelectionFrame frame = frames.putIfAbsent(id, new SelectionFrame(this, adapter, uiState));
                final Shape selection = modelState.getShape(id);
                frame.update(
                        parseDouble(selection.getX()),
                        parseDouble(selection.getY()),
                        parseDouble(selection.getWidth()),
                        parseDouble(selection.getHeight())
                );
            });
            return;
        }

        modelState.findTopShapeAt(mouseX, mouseY)
                .ifPresentOrElse(element -> {
                    final SelectionFrame frame = frames.putIfAbsent(element.getId(), new SelectionFrame(this, adapter, uiState));
                    frame.update(
                        parseDouble(element.getX()),
                        parseDouble(element.getY()),
                        parseDouble(element.getWidth()),
                        parseDouble(element.getHeight()));
                }, this::reset);
    }

/*    public void update(final int id, final double x, final double y, final double width, final double height) {
        if (frames.containsKey(id)) {
            frames.get(id).update(x, y, width, height);
            this.isVisible = true;
        }
        this.x = x;
        this.y = y;
        this.width = Math.max(width, MINIMAL_SIDE_SIZE);
        this.height = Math.max(height, MINIMAL_SIDE_SIZE);

        if (!uiState.isMultipleSelection()) {
            contactPoints.forEach(contact -> contact.update(this.x, this.y, this.width, this.height));
        }
    }*/

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        return frames.values().stream().anyMatch(frame -> frame.onDragDetected(event));
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        frames.values().forEach(frame -> frame.onMouseDragged(mouseX, mouseY));
    }

    @Override
    public void onMouseReleased(final MouseEvent event) {
        frames.values().forEach(frame -> frame.onMouseReleased(event));
    }

    public boolean isInBounds(final double x, final double y) {
        return frames.values().stream().anyMatch(frame -> frame.isInBounds(x, y));
    }

    public void makeVisible() {
        frames.values().forEach(SelectionFrame::makeVisible);
        this.isVisible = true;
    }

    public class SelectionFrame extends CanvasTool implements CanvasClickable, CanvasDraggable {
        private final UIState uiState;

        private static final double FRAME_WIDTH = 3.0;
        private final Color fillColor;
        private final List<ContactPoint> contactPoints;

        SelectionFrame(
                final ShapeSelectionTool selectionTool,
                final CanvasAdapter adapter,
                final UIState uiState) {
            super(-1, -1, -1, -1, 0);

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
        public double[] reset() {
            contactPoints.forEach(ContactPoint::reset);
            return super.reset();
        }

        public void update(final double x, final double y, final double width, final double height) {
            this.x = x;
            this.y = y;
            this.width = Math.max(width, MINIMAL_SIDE_SIZE);
            this.height = Math.max(height, MINIMAL_SIDE_SIZE);

            if (!uiState.isMultipleSelection()) {
                contactPoints.forEach(contact -> contact.update(this.x, this.y, this.width, this.height));
            }
            this.isVisible = true;
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

        public boolean isInBounds(final double x, final double y) {
            final boolean isInShapeBounds =
                    x >= this.x && x <= this.x + this.width
                            && y >= this.y && y <= this.y + this.height;

            return isInShapeBounds ? isInShapeBounds : contactPoints.stream().anyMatch(contact -> contact.isInBounds(x, y));
        }

        public void makeVisible() {
            ShapeSelectionTool.this.makeVisible();
        }
    }
}
