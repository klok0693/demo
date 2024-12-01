package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.input.MouseEvent;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.mode.InsertModeSwitchable;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.port.ui.canvas.tool.draggable.CanvasDraggable;
import org.example.astero_demo.port.ui.canvas.tool.draggable.drag.DragShapeTool;
import org.example.astero_demo.port.ui.canvas.tool.draggable.insert.InsertTool;

/**
 * Layer to for a tool elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ToolLayer extends CanvasLayer<CanvasTool>
        implements CanvasClickable, CanvasDraggable, UpdatableView, InsertModeSwitchable {
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

    @Override
    public void update() {
        if (uiState.isInInsertMode() || !uiState.hasSelectedId()) {
            resetAll();
        }
        selectionTool.update();
    }

    @Override
    public void switchToInsertMode() {
        resetAll();
        selectionTool.update();
    }

    public boolean isInBounds(final double x, final double y) {
        return selectionTool.isInBounds(x, y);
    }

    @Override
    public void onMousePressed(final MouseEvent event) {
        if (uiState.isInInsertMode()) {
            insertTool.onMousePressed(event);
        }
        else {
            selectionTool.onMousePressed(event);
        }
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        if (uiState.isInInsertMode()) {
            return insertTool.onDragDetected(event);
        }
        if (selectionTool.onDragDetected(event)) {
            return true;
        }
        return uiState.hasSelectedId() && dragTool.onDragDetected(event);
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        selectionTool.onMouseDragged(mouseX, mouseY);
        dragTool.onMouseDragged(mouseX, mouseY);
        insertTool.onMouseDragged(mouseX, mouseY);
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

    private void resetAll() {
        getChildren().forEach(CanvasTool::reset);
    }
}
