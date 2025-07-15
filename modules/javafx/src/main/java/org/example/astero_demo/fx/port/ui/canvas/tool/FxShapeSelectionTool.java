package org.example.astero_demo.fx.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.tool.SelectionFrame;
import org.example.astero_demo.core.port.ui.canvas.tool.ShapeSelectionTool;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.CanvasDraggable;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.selection.FxModificableSelectionFrame;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class FxShapeSelectionTool extends FxCanvasTool implements ShapeSelectionTool<GraphicsContext>, CanvasDraggable {
    private final ModelState modelState;
    private final UIState uiState;
    private final CanvasAdapter adapter;

    private final Map<Integer, FxModificableSelectionFrame> frames = new HashMap<>(2);

    public FxShapeSelectionTool(
            final ModelState modelState,
            final UIState uiState,
            final CanvasAdapter adapter) {
        super(-1, -1, -1, -1, 0);
        this.modelState = modelState;
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
                final FxModificableSelectionFrame frame = new FxModificableSelectionFrame(adapter, uiState);

                final double frameX = parseDouble(selection.getX());
                final double frameY = parseDouble(selection.getY());
                final double frameWidth = parseDouble(selection.getWidth());
                final double frameHeight = parseDouble(selection.getHeight());
                frame.update(frameX, frameY, frameWidth, frameHeight);
                frames.put(id, frame);

                FxShapeSelectionTool.this.setVisible(true);
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
                final SelectionFrame frame = frames.putIfAbsent(id, new FxModificableSelectionFrame(adapter, uiState));
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
                    final SelectionFrame frame = frames.putIfAbsent(element.getId(), new FxModificableSelectionFrame(adapter, uiState));
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

    @Override
    public boolean isInBounds(final double x, final double y) {
        return frames.values().stream().anyMatch(frame -> frame.isInBounds(x, y));
    }
}
