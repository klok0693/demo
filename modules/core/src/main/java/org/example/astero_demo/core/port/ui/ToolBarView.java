package org.example.astero_demo.core.port.ui;

import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitchableView;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarAdapter;

/**
 * Represents a view for the tool bar panel.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ToolBarView implements ModeSwitchableView {
    private final ToolBarAdapter operationProcessor;
    private final UIState uiState;

    public ToolBarView(final UIState uiState, final ToolBarAdapter operationProcessor) {
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
        setInsertRectBtnSelected(true);
        setInsertCycleBtnSelected(true);
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

    public void onInsertRectAction() {
        operationProcessor.onInsertRectAction();
    }

    public void onInsertCycleAction() {
        operationProcessor.onInsertCycleAction();
    }

    public void onDeleteAction() {
        operationProcessor.onDeleteAction();
    }

    public void onUndoAction() {
        operationProcessor.onUndoAction();
    }
}
