package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.port.ui.canvas.CanvasView;

import java.util.function.Consumer;

public class ToolLayer extends CanvasLayer<CanvasTool> implements CanvasClickable, CanvasDraggable {

    private final ShapeSelectionTool selectionTool;
    private final DragShapeTool dragTool;
    private final InsertTool insertTool;

    private final UIState uiState;

    public ToolLayer(
            final ShapeSelectionTool selectionTool,
            final DragShapeTool dragTool,
            final InsertTool insertTool,
            final UIState uiState) {
        super(2);

        this.selectionTool = selectionTool;
        this.uiState = uiState;
        add(selectionTool);

        this.dragTool = dragTool;
        add(dragTool);

        this.insertTool = insertTool;
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
    public void onMouseDragged(final double mouseX, final double mouseY) {
        forEachChildren(CanvasDraggable.class, draggable -> draggable.onMouseDragged(mouseX, mouseY));
    }

    @Override
    public void onMouseReleased(final MouseEvent event, final boolean isOnBounds) {
        if (uiState.isInInsertMode()) {
            insertTool.onMouseReleased(event, isOnBounds);
        }
        else if (uiState.hasSelectedId()) {
            dragTool.onMouseReleased(event, isOnBounds);
            selectionTool.onMouseReleased(event, isOnBounds);
        }
    }

    public void resetAll() {
        getChildren().forEach(CanvasTool::reset);
    }

    private <T> void forEachChildren(final Class<T> tClass, final Consumer<T> consumer) {

        getChildren()
                .filter(tool -> tClass.isAssignableFrom(tool.getClass()))
                .map(tClass::cast)
                .forEach(consumer::accept);
    }
}
