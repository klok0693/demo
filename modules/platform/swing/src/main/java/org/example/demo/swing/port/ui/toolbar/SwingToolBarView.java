package org.example.demo.swing.port.ui.toolbar;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.toolbar.ToolBarAdapter;
import org.example.demo.core.port.ui.ToolBarPanelView;

/**
 * Swing realization of {@link ToolBarPanelView}
 *
 * @since 1.2
 * @author Pilip Yurchanka
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
