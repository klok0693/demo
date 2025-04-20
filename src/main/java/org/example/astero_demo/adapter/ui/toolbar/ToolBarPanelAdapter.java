package org.example.astero_demo.adapter.ui.toolbar;

import org.example.astero_demo.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.event.InsertModeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.logic.EventProcessor;
import org.example.astero_demo.port.ui.ToolBarView;

/**
 * Leaf adapter for interacting with a Tool Bar Panel
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ToolBarPanelAdapter extends LeafAdapter implements ToolBarAdapter {
    private final OperationAdapter keyBoardAdapter;
    private final ToolBarView toolBarView;

    public ToolBarPanelAdapter(
            final EventProcessor controller,
            final UIState uiState,
            final OperationAdapter keyBoardAdapter,
            final ToolBarView toolBarView,
            final ParentAdapter parentAdapter) {
        super(controller, uiState, parentAdapter);
        this.keyBoardAdapter = keyBoardAdapter;
        this.toolBarView = toolBarView;
    }

    @Override
    public void update() {
        toolBarView.update();
    }

    @Override
    public void switchMode(final UIMode mode) {
        toolBarView.switchMode(mode);
    }

    @Override
    public void onInsertRectAction() {
        sendEvent(new InsertModeEvent(ShapeType.RECT));
    }

    @Override
    public void onInsertCycleAction() {
        sendEvent(new InsertModeEvent(ShapeType.ELLIPSE));
    }

    @Override
    public void onDeleteAction() {
        keyBoardAdapter.handleDelete();
    }

    @Override
    public void onUndoAction() {
        keyBoardAdapter.handleUndo();
    }
}
