package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.port.ui.canvas.CanvasView;

import java.util.function.Consumer;

public class ToolLayer extends CanvasLayer<CanvasTool> implements CanvasClickable, CanvasDraggable {
    private final CanvasView canvasView;

    public ToolLayer(final GraphicsContext gc, final CanvasView canvasView) {
        super(gc, 2);
        this.canvasView = canvasView;

        add(new ShapeSelectionTool(canvasView));
        add(new DragShapeTool(canvasView));
    }

    public boolean isInBounds(final double x, final double y) {
        return getChildren()
                .filter(tool -> ShapeSelectionTool.class.isAssignableFrom(tool.getClass()))
                .map(ShapeSelectionTool.class::cast)
                .anyMatch(tool -> tool.isInBounds(x, y));

    }

    @Override
    public void onMousePressed(final double x, final double y) {
        resetAll();
        forEachChildren(CanvasClickable.class, clickable -> clickable.onMousePressed(x, y));
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        return getChildren()
                .filter(tool -> CanvasDraggable.class.isAssignableFrom(tool.getClass()))
                .map(CanvasDraggable.class::cast)
                .anyMatch(draggable -> draggable.onDragDetected(event));

        //forEachChildren(CanvasDraggable.class, draggable -> draggable.onDragDetected(event));
    }

    @Override
    public void onMouseDragged(final MouseEvent event) {
        forEachChildren(CanvasDraggable.class, draggable -> draggable.onMouseDragged(event));
    }

    @Override
    public void onMouseReleased(final MouseEvent event) {
        forEachChildren(CanvasDraggable.class, draggable -> draggable.onMouseReleased(event));
    }

    public void resetAll() {
        getChildren().forEach(CanvasTool::reset);
    }

    public void setUIState(final UIState state) {
        forEachChildren(DragShapeTool.class, dragTool -> dragTool.setUiState(state));
    }

    private <T> void forEachChildren(final Class<T> tClass, final Consumer<T> consumer) {

        getChildren()
                .filter(tool -> tClass.isAssignableFrom(tool.getClass()))
                .map(tClass::cast)
                .forEach(consumer::accept);
    }
}
