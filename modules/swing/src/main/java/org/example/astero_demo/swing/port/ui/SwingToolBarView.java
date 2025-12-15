package org.example.astero_demo.swing.port.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.core.port.ui.ToolBarView;

/**
 * JavaFX's realization of {@link ToolBarView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingToolBarView extends ToolBarView {
/*    public ToggleButton insertRectBtn;
    public ToggleButton insertCycleBtn;
    public Button deleteBtn;
    public Button undoBtn;*/

    public SwingToolBarView(
            final UIState uiState,
            final ToolBarAdapter operationProcessor) {
        super(uiState, operationProcessor);
    }

    @Override
    protected void setDeleteBtnDisabled(final boolean isDisabled) {
        //deleteBtn.setDisable(isDisabled);
    }

    @Override
    protected void setInsertRectBtnSelected(final boolean setSelected) {
        //insertRectBtn.setSelected(setSelected);
    }

    @Override
    protected void setInsertCycleBtnSelected(final boolean setSelected) {
        //insertCycleBtn.setSelected(setSelected);
    }
}
