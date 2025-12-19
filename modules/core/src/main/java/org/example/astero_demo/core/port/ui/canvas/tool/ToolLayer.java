package org.example.astero_demo.core.port.ui.canvas.tool;

import org.example.astero_demo.api.graphics.GraphicsPainter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitchableView;
import org.example.astero_demo.core.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.drag.DragShapeTool;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert.InsertShapeTool;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ShapeSelectionTool;

/**
 * Layer to for a tool elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ToolLayer<E extends GraphicsPainter> extends CanvasLayer<E, CanvasTool<E>>
        implements ModeSwitchableView, CanvasDraggable, CanvasClickable {
    protected final ShapeSelectionTool<E> selectionTool;
    protected final DragShapeTool<E> dragTool;
    protected final InsertShapeTool<E> insertTool;
    protected final UIState uiState;

    protected ToolLayer(
            final ShapeSelectionTool<E> selectionTool,
            final DragShapeTool<E> dragTool,
            final InsertShapeTool<E> insertTool,
            final UIState uiState) {
        super(2);
        this.selectionTool = selectionTool;
        this.dragTool = dragTool;
        this.insertTool = insertTool;
        this.uiState = uiState;

        add(selectionTool);
        add(dragTool);
        add(insertTool);
    }

    @Override
    public void update() {
        if (!uiState.hasSelectedId()) {
            resetAll();
        }
        else {
            selectionTool.update();
        }
    }

    @Override
    public void switchToInsertMode() {
        resetAll();
        insertTool.setEnabled(true);
        dragTool.setEnabled(false);
        selectionTool.setEnabled(false);
    }

    @Override
    public void switchToSingleSelectionMode() {
        resetAll();
        insertTool.setEnabled(false);
        dragTool.setEnabled(true);
        selectionTool.setEnabled(true);
    }

    @Override
    public void switchToMultipleSelectionMode() {
        resetAll();
        insertTool.setEnabled(false);
        dragTool.setEnabled(true);
        selectionTool.setEnabled(true);
    }

    @Override
    public void onMousePressed(final double mouseX, final double mouseY, final boolean isShiftDown) {
        insertTool.onMousePressed(mouseX, mouseY, isShiftDown);
        selectionTool.onMousePressed(mouseX, mouseY, isShiftDown);
    }

    @Override
    public boolean onDragDetected(final double mouseX, final double mouseY) {
        return insertTool.onDragDetected(mouseX, mouseY)
                || selectionTool.onDragDetected(mouseX, mouseY)
                || (uiState.hasSelectedId() && dragTool.onDragDetected(mouseX, mouseY));
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        selectionTool.onMouseDragged(mouseX, mouseY);
        dragTool.onMouseDragged(mouseX, mouseY);
        insertTool.onMouseDragged(mouseX, mouseY);
    }

    @Override
    public void onMouseReleased(final double mouseX, final double mouseY) {
        insertTool.onMouseReleased(mouseX, mouseY);
        dragTool.onMouseReleased(mouseX, mouseY);
        selectionTool.onMouseReleased(mouseX, mouseY);
    }

    public boolean isInBounds(final double x, final double y) {
        return selectionTool.isInBounds(x, y);
    }

    private void resetAll() {
        getChildren().forEach(CanvasTool::reset);
    }
}
