package org.example.astero_demo.port.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.mode.InsertModeSwitchable;
import org.example.astero_demo.adapter.ui.state.mode.ModeSwitchable;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;

/**
 * Represents a view for the tool bar panel.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ToolBarView implements UpdatableView, InsertModeSwitchable {
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
        final boolean needToUnStickBtn = uiState.isInInsertMode();
        insertRectBtn.setSelected(needToUnStickBtn);
        insertCycleBtn.setSelected(needToUnStickBtn);

        final boolean disableRelated = uiState.isInInsertMode() || !uiState.hasSelectedId();
        deleteBtn.setDisable(disableRelated);
    }

    @Override
    public void switchToInsertMode() {
        insertRectBtn.setSelected(true);
        insertCycleBtn.setSelected(true);
        deleteBtn.setDisable(true);
    }

    public void onInsertRectAction(final ActionEvent event) {
        operationProcessor.onInsertRectAction();
    }

    public void onInsertCycleAction(final ActionEvent event) {
        operationProcessor.onInsertCycleAction();
    }

    public void onDeleteAction(final ActionEvent event) {
        operationProcessor.onDeleteAction();
    }

    public void onUndoAction(final ActionEvent event) {
        operationProcessor.onUndoAction();
    }
}
