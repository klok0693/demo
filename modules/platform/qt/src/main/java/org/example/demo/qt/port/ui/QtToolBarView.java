package org.example.demo.qt.port.ui;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.demo.core.port.ui.ToolBarPanelView;

/**
 * JavaFX's realization of {@link ToolBarPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtToolBarView extends ToolBarPanelView {

    public QtToolBarView(
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
