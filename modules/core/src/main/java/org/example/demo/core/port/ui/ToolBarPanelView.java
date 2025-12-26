package org.example.demo.core.port.ui;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.demo.model.entity.ShapeType;

/**
 * Represents a view for the tool bar panel.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ToolBarPanelView implements ToolBarView {
    private final ToolBarAdapter operationProcessor;
    private final UIState uiState;

    public ToolBarPanelView(final UIState uiState, final ToolBarAdapter operationProcessor) {
        this.uiState = uiState;
        this.operationProcessor = operationProcessor;
    }

    @Override
    public void update() {
        final boolean disableRelated = !uiState.hasSelectedId() || uiState.isMultipleSelection();
        setDeleteBtnDisabled(disableRelated);
    }

    @Override
    public void switchToInsertMode() {
        setInsertRectBtnSelected(uiState.getInsertShapeType() != ShapeType.RECT);
        setInsertCycleBtnSelected(uiState.getInsertShapeType() != ShapeType.ELLIPSE);
        setDeleteBtnDisabled(true);
    }

    @Override
    public void switchToSingleSelectionMode() {
        setInsertRectBtnSelected(false);
        setInsertCycleBtnSelected(false);
        setDeleteBtnDisabled(!uiState.hasSelectedId());
    }

    @Override
    public void switchToMultipleSelectionMode() {
        setInsertRectBtnSelected(false);
        setInsertCycleBtnSelected(false);
        setDeleteBtnDisabled(true);
    }

    protected abstract void setDeleteBtnDisabled(boolean isDisabled);

    protected abstract void setInsertRectBtnSelected(boolean setSelected);

    protected abstract void setInsertCycleBtnSelected(boolean setSelected);

    @Override
    public void onInsertRectAction() {
        operationProcessor.onInsertRectAction();
    }

    @Override
    public void onInsertCycleAction() {
        operationProcessor.onInsertCycleAction();
    }

    @Override
    public void onDeleteAction() {
        operationProcessor.onDeleteAction();
    }

    @Override
    public void onUndoAction() {
        operationProcessor.onUndoAction();
    }
}
