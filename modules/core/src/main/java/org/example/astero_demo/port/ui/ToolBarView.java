package org.example.astero_demo.port.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.mode.InsertModeSwitchable;
import org.example.astero_demo.adapter.ui.state.mode.ModeSwitchable;
import org.example.astero_demo.adapter.ui.state.mode.ModeSwitchableView;
import org.example.astero_demo.adapter.ui.state.mode.SingleSelectionModeSwitchable;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;

/**
 * Represents a view for the tool bar panel.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ToolBarView implements ModeSwitchableView {
    private final ToolBarAdapter operationProcessor;
    private final UIState uiState;
    public ToggleButton insertRectBtn;
    public ToggleButton insertCycleBtn;
    public Button deleteBtn;
    public Button undoBtn;

    public ToolBarView(final UIState uiState, final ToolBarAdapter operationProcessor) {
        this.uiState = uiState;
        this.operationProcessor = operationProcessor;
    }

    @Override
    public void update() {
        final boolean disableRelated = !uiState.hasSelectedId() || uiState.isMultipleSelection();
        deleteBtn.setDisable(disableRelated);
    }

    @Override
    public void switchToInsertMode() {
        insertRectBtn.setSelected(true);
        insertCycleBtn.setSelected(true);
        deleteBtn.setDisable(true);
    }

    @Override
    public void switchToSingleSelectionMode() {
        insertRectBtn.setSelected(false);
        insertCycleBtn.setSelected(false);
        deleteBtn.setDisable(!uiState.hasSelectedId());
    }

    @Override
    public void switchToMultipleSelectionMode() {
        insertRectBtn.setSelected(false);
        insertCycleBtn.setSelected(false);
        deleteBtn.setDisable(true);
    }

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
