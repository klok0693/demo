package org.example.astero_demo.fx.port.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.core.port.ui.ToolBarPanelView;

/**
 * JavaFX's realization of {@link ToolBarPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxToolBarView extends ToolBarPanelView {
    public ToggleButton insertRectBtn;
    public ToggleButton insertCycleBtn;
    public Button deleteBtn;
    public Button undoBtn;

    public FxToolBarView(
            final UIState uiState,
            final ToolBarAdapter operationProcessor) {
        super(uiState, operationProcessor);
    }

    @Override
    protected void setDeleteBtnDisabled(final boolean isDisabled) {
        deleteBtn.setDisable(isDisabled);
    }

    @Override
    protected void setInsertRectBtnSelected(final boolean setSelected) {
        insertRectBtn.setSelected(setSelected);
    }

    @Override
    protected void setInsertCycleBtnSelected(final boolean setSelected) {
        insertCycleBtn.setSelected(setSelected);
    }
}
