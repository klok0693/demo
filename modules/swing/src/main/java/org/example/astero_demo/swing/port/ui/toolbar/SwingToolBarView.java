package org.example.astero_demo.swing.port.ui.toolbar;

import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.core.port.ui.ToolBarPanelView;

/**
 * JavaFX's realization of {@link SwitchableToolBarView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingToolBarView extends ToolBarPanelView {
    private final ToolBarUI ui;

    public SwingToolBarView(
            final UIState uiState,
            final ToolBarAdapter operationProcessor,
            final ToolBarUI ui) {
        super(uiState, operationProcessor);
        this.ui = ui;
    }

    @Override
    public void setDeleteBtnDisabled(final boolean isDisabled) {
        ui.setDeleteBtnDisabled(isDisabled);
    }

    @Override
    public void setInsertRectBtnSelected(final boolean setSelected) {
        ui.setInsertRectBtnSelected(setSelected);
    }

    @Override
    public void setInsertCycleBtnSelected(final boolean setSelected) {
        ui.setInsertCycleBtnSelected(setSelected);
    }
}
