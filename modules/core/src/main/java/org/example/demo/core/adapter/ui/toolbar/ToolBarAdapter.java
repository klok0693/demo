package org.example.demo.core.adapter.ui.toolbar;

import org.example.demo.core.adapter.ui.UpdatableAdapter;
import org.example.demo.core.adapter.ui.state.mode.ModeSwitcher;

/**
 * Represents an interface for interact with Tool Bar Panel
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface ToolBarAdapter extends UpdatableAdapter, ModeSwitcher {

    void onInsertRectAction();

    void onInsertCycleAction();

    void onDeleteAction();

    void onUndoAction();
}
