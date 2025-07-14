package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.state.ModelState;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.tool.draggable.CanvasDraggable;
import org.example.astero_demo.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;

import java.util.*;

import static java.lang.Double.parseDouble;

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

    private final Map<Integer, ModificableSelectionFrame> frames = new HashMap<>(2);

    public ShapeSelectionTool(final CanvasAdapter adapter, final ModelState holder, final UIState uiState) {
        super(-1, -1, -1, -1, 0);
        this.modelState = holder;
        this.uiState = uiState;
        this.adapter = adapter;
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
                final ModificableSelectionFrame frame = new ModificableSelectionFrame(adapter, uiState);

                final double frameX = parseDouble(selection.getX());
                final double frameY = parseDouble(selection.getY());
                final double frameWidth = parseDouble(selection.getWidth());
                final double frameHeight = parseDouble(selection.getHeight());
                frame.update(frameX, frameY, frameWidth, frameHeight);
                frames.put(id, frame);

                ShapeSelectionTool.this.setVisible(true);
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
        if (!isEnabled()) {
            return;
        }
        final double mouseX = event.getX();
        final double mouseY = event.getY();

/*        if (event.isControlDown() && uiState.hasSelectedId()) {
            adapter.selectMultiple(mouseX, mouseY);
            return;
        }*/

        if (event.isShiftDown() && uiState.hasSelectedId() && !uiState.isMultipleSelection()) {
            adapter.selectNextShapeAt(mouseX, mouseY);
            return;
        }

        frames.values().forEach(frame -> frame.onMousePressed(event));
        if (isVisible() && uiState.hasSelectedId()) {
            uiState.getSelectedIds().forEach(id -> {
                final SelectionFrame frame = frames.putIfAbsent(id, new ModificableSelectionFrame(adapter, uiState));
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
                    final SelectionFrame frame = frames.putIfAbsent(element.getId(), new ModificableSelectionFrame(adapter, uiState));
                    frame.update(
                        parseDouble(element.getX()),
                        parseDouble(element.getY()),
                        parseDouble(element.getWidth()),
                        parseDouble(element.getHeight()));
                }, this::reset);
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        if (!isEnabled()) {
            return false;
        }
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
}
