package org.example.astero_demo.fx.port.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.core.port.ui.ToolBarView;

public class FxToolBarView extends ToolBarView {
    public ToggleButton insertRectBtn;
    public ToggleButton insertCycleBtn;
    public Button deleteBtn;
    public Button undoBtn;

    public FxToolBarView(UIState uiState, ToolBarAdapter operationProcessor) {
        super(uiState, operationProcessor);
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
}
