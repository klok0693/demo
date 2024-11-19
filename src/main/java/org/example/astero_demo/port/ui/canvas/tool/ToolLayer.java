package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.input.MouseEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.port.ui.canvas.tool.draggable.CanvasDraggable;
import org.example.astero_demo.port.ui.canvas.tool.draggable.DragShapeTool;
import org.example.astero_demo.port.ui.canvas.tool.draggable.InsertTool;

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
        this.uiState = uiState;

        this.selectionTool = selectionTool;
        add(selectionTool);

        this.dragTool = dragTool;
        add(dragTool);

        this.insertTool = insertTool;
        add(insertTool);
    }

    public boolean isInBounds(final double x, final double y) {
        return selectionTool.isInBounds(x, y);
    }

    public void selectElement(final int id) {
        selectionTool.selectElement(id);
    }

    @Override
    public void onMousePressed(final double x, final double y) {
        if (uiState.isInInsertMode()) {
            insertTool.onMousePressed(x, y);
        }
        else {
            selectionTool.onMousePressed(x, y);
        }
    }

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        if (uiState.isInInsertMode()) {
            return insertTool.onDragDetected(mouseX, mouseY);
        }
        if (selectionTool.onDragDetected(mouseX, mouseY)) {
            return true;
        }
        return uiState.hasSelectedId() && dragTool.onDragDetected(mouseX, mouseY);
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        forEachChildren(CanvasDraggable.class, draggable -> draggable.onMouseDragged(mouseX, mouseY));
    }

    @Override
    public void onMouseReleased(final MouseEvent event) {
        if (uiState.isInInsertMode()) {
            insertTool.onMouseReleased(event);
        }
        else if (uiState.hasSelectedId()) {
            dragTool.onMouseReleased(event);
            selectionTool.onMouseReleased(event);
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
