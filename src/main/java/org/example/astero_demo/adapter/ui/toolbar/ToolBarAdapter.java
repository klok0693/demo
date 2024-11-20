package org.example.astero_demo.adapter.ui.toolbar;

import org.example.astero_demo.adapter.ui.UpdatableAdapter;

public interface ToolBarAdapter extends UpdatableAdapter {
    void onInsertRectAction();

    void onInsertCycleAction();

    void onDeleteAction();

    void onUndoAction();
}
