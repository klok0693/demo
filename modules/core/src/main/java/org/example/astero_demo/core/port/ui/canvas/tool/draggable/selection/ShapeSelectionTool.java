package org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection;

import org.example.astero_demo.api.graphics.GraphicsPainter;
import org.example.astero_demo.core.adapter.ui.UpdatableView;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.tool.CanvasDraggable;
import org.example.astero_demo.core.port.ui.canvas.tool.CanvasTool;
import org.example.astero_demo.core.port.ui.canvas.tool.CanvasClickable;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;

/**
 * Canvas tool, allowing to select shape element on a canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ShapeSelectionTool<E extends GraphicsPainter>
        extends CanvasTool<E> implements UpdatableView, CanvasDraggable, CanvasClickable {

    private final ModelState modelState;
    private final UIState uiState;
    private final CanvasAdapter adapter;

    private final Map<Integer, ModificableSelectionFrame<E>> frames = new HashMap<>(2);

    protected ShapeSelectionTool(
            final ModelState modelState,
            final UIState uiState,
            final CanvasAdapter adapter) {
        super(-1, -1, -1, -1, 0);
        this.modelState = modelState;
        this.uiState = uiState;
        this.adapter = adapter;
    }

    @Override
    protected void drawElement(final E gc) {
        frames.values().forEach(frame -> frame.draw(gc));
    }

    @Override
    public void update() {
        reset();

        if (uiState.hasSelectedId()) {
            uiState.getSelectedIds().forEach(id -> {
                final Shape selection = modelState.getShape(id);
                final ModificableSelectionFrame<E> frame = createModificableFrame(adapter, uiState);

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
    public void onMousePressed(final double mouseX, final double mouseY, final boolean isShiftDown) {
        if (!isEnabled()) {
            return;
        }

/*        if (event.isControlDown() && uiState.hasSelectedId()) {
            adapter.selectMultiple(mouseX, mouseY);
            return;
        }*/

        if (isShiftDown && uiState.hasSelectedId() && !uiState.isMultipleSelection()) {
            adapter.selectNextShapeAt(mouseX, mouseY);
            return;
        }

        frames.values().forEach(frame -> frame.onMousePressed(mouseX, mouseY, isShiftDown));
        if (isVisible() && uiState.hasSelectedId()) {
            uiState.getSelectedIds().forEach(id -> {
                final SelectionFrame<E> frame = frames.putIfAbsent(id, createModificableFrame(adapter, uiState));
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
                    final SelectionFrame<E> frame = frames.putIfAbsent(element.getId(), createModificableFrame(adapter, uiState));
                    frame.update(
                            parseDouble(element.getX()),
                            parseDouble(element.getY()),
                            parseDouble(element.getWidth()),
                            parseDouble(element.getHeight()));
                }, this::reset);
    }

    protected abstract ModificableSelectionFrame<E> createModificableFrame(CanvasAdapter adapter, UIState uiState);

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        if (!isEnabled()) {
            return false;
        }
        return frames.values().stream().anyMatch(frame -> frame.onDragDetected(mouseX, mouseY));
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        frames.values().forEach(frame -> frame.onMouseDragged(mouseX, mouseY));
    }

    @Override
    public void onMouseReleased(final double mouseX, final double mouseY) {
        frames.values().forEach(frame -> frame.onMouseReleased(mouseX, mouseY));
    }

    public boolean isInBounds(final double x, final double y) {
        return frames.values().stream().anyMatch(frame -> frame.isInBounds(x, y));
    }
}
