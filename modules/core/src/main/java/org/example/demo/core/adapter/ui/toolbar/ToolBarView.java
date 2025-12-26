package org.example.demo.core.adapter.ui.toolbar;

import org.example.demo.core.adapter.ui.state.mode.ModeSwitchableView;

public interface ToolBarView extends ModeSwitchableView {

    void onInsertRectAction();

    void onInsertCycleAction();

    void onDeleteAction();

    void onUndoAction();
}
