package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.port.ui.canvas.CanvasView;

import java.util.function.Consumer;

public class ToolLayer extends CanvasLayer<CanvasTool> implements CanvasClickable, CanvasDraggable {
    private final CanvasView canvasView;

    private final ShapeSelectionTool selectionTool;
    private final DragShapeTool dragTool;
    private final InsertTool insertTool;

    private UIState uiState;

    public ToolLayer(final GraphicsContext gc, final CanvasView canvasView) {
        super(gc, 2);
        this.canvasView = canvasView;

        this.selectionTool = new ShapeSelectionTool(canvasView, 0);
        add(selectionTool);

        this.dragTool = new DragShapeTool(canvasView, 1);
        add(dragTool);

        this.insertTool = new InsertTool(canvasView, 2);
        add(insertTool);
    }

    public boolean isInBounds(final double x, final double y) {
        return selectionTool.isInBounds(x, y);
    }

    @Override
    public void onMousePressed(final double x, final double y) {
        resetAll();
        if (uiState.isInInsertMode()) {
            insertTool.onMousePressed(x, y);
        }
        else {
            selectionTool.onMousePressed(x, y);
        }
        //forEachChildren(CanvasClickable.class, clickable -> clickable.onMousePressed(x, y));
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        if (uiState.isInInsertMode()) {
            return insertTool.onDragDetected(event);
        }
        final boolean isOnContactPoint = selectionTool.onDragDetected(event);
        if (!isOnContactPoint) {
            if (uiState.hasSelectedId()) {
                dragTool.onDragDetected(event);
                return true;
            }
            else return false;
        }
        else return isOnContactPoint;
    }

/*    public boolean hasActiveTool() {
        return selectionTool.isActive || dragTool.isNeedToDrag();
    }*/

    @Override
    public void onMouseDragged(final MouseEvent event) {
        forEachChildren(CanvasDraggable.class, draggable -> draggable.onMouseDragged(event));
    }

    @Override
    public void onMouseReleased(final MouseEvent event) {
        if (uiState.isInInsertMode()) {
            insertTool.onMouseReleased(event);
        }
        else if (uiState.hasSelectedId()) {
            dragTool.onMouseReleased(event);
            selectionTool.onMouseReleased(event);
            //forEachChildren(CanvasDraggable.class, draggable -> draggable.onMouseReleased(event));
        }
    }

    public void resetAll() {
        getChildren().forEach(CanvasTool::reset);
    }

    public void setUIState(final UIState state) {
        this.uiState = state;
        dragTool.setUiState(state);
        insertTool.setUiState(uiState);
    }

    private <T> void forEachChildren(final Class<T> tClass, final Consumer<T> consumer) {

        getChildren()
                .filter(tool -> tClass.isAssignableFrom(tool.getClass()))
                .map(tClass::cast)
                .forEach(consumer::accept);
    }
}
