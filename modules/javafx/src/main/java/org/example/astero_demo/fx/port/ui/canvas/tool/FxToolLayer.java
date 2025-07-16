package org.example.astero_demo.fx.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.CanvasTool;
import org.example.astero_demo.core.port.ui.canvas.tool.ToolLayer;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.CanvasDraggable;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.drag.FxDragShapeTool;
import org.example.astero_demo.fx.port.ui.canvas.tool.draggable.insert.FxInsertShapeTool;

/**
 * JavaFX's realization of {@link ToolLayer}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxToolLayer extends ToolLayer<GraphicsContext> implements CanvasDraggable, CanvasClickable {
    protected final FxShapeSelectionTool selectionTool;
    protected final FxDragShapeTool dragTool;
    protected final FxInsertShapeTool insertTool;
    protected final UIState uiState;

    public FxToolLayer(
            final FxShapeSelectionTool selectionTool,
            final FxDragShapeTool dragTool,
            final FxInsertShapeTool insertTool,
            final UIState uiState) {
        super();
        this.uiState = uiState;

        this.selectionTool = selectionTool;
        add(selectionTool);

        this.dragTool = dragTool;
        add(dragTool);

        this.insertTool = insertTool;
        add(insertTool);
    }

    @Override
    public void onMousePressed(final MouseEvent event) {
        insertTool.onMousePressed(event);
        selectionTool.onMousePressed(event);
    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        return insertTool.onDragDetected(event)
                || selectionTool.onDragDetected(event)
                || (uiState.hasSelectedId() && dragTool.onDragDetected(event));
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        selectionTool.onMouseDragged(mouseX, mouseY);
        dragTool.onMouseDragged(mouseX, mouseY);
        insertTool.onMouseDragged(mouseX, mouseY);
    }

    @Override
    public void onMouseReleased(final MouseEvent event) {
        insertTool.onMouseReleased(event);
        dragTool.onMouseReleased(event);
        selectionTool.onMouseReleased(event);
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

    public boolean isInBounds(final double x, final double y) {
        return selectionTool.isInBounds(x, y);
    }

    private void resetAll() {
        getChildren().forEach(CanvasTool::reset);
    }
}
