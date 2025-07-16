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
    protected final UIState uiState;

    public ToolBarView(final UIState uiState, final ToolBarAdapter operationProcessor) {
        this.uiState = uiState;
        this.operationProcessor = operationProcessor;
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
