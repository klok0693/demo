package org.example.astero_demo.port.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.event.InsertModeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.toolbar.OperationProcessor;
import org.example.astero_demo.controller.ViewController;

public class ToolBarView implements UpdatableView {
    private final OperationProcessor operationProcessor;
    private final UIState uiState;
    public ToggleButton rectBtn;
    public ToggleButton cycleBtn;
    public Button deleteBtn;
    public Button undoBtn;

    public ToolBarView(final UIState uiState, final OperationProcessor operationProcessor) {
        this.uiState = uiState;
        this.operationProcessor = operationProcessor;
    }

    @Override
    public void update() {
        final boolean needToUnStickBtn = uiState.isInInsertMode();
        rectBtn.setSelected(needToUnStickBtn);
        cycleBtn.setSelected(needToUnStickBtn);

        final boolean disableRelated = uiState.isInInsertMode() || !uiState.hasSelectedId();
        deleteBtn.setDisable(disableRelated);
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
