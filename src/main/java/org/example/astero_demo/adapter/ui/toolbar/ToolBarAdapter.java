package org.example.astero_demo.adapter.ui.toolbar;

import org.example.astero_demo.adapter.ui.UpdatableAdapter;

/**
 * Represents an interface for interact with Tool Bar Panel
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface ToolBarAdapter extends UpdatableAdapter {

    void onInsertRectAction();

    void onInsertCycleAction();

    void onDeleteAction();

    void onUndoAction();
}
