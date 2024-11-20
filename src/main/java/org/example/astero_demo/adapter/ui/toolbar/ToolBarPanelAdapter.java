package org.example.astero_demo.adapter.ui.toolbar;

import javafx.scene.input.KeyCode;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.event.InsertModeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.port.ui.ToolBarView;

public class ToolBarPanelAdapter extends LeafAdapter implements ToolBarAdapter {

    private final RootShortcutHandler shortcutHandler; // TODO: remove
    private final ToolBarView toolBarView;

    public ToolBarPanelAdapter(
            final LogicEventProcessor controller,
            final UIState uiState,
            final RootShortcutHandler shortcutHandler,
            final ToolBarView toolBarView,
            final ParentAdapter parentAdapter) {
        super(controller, uiState, parentAdapter);
        this.shortcutHandler = shortcutHandler;
        this.toolBarView = toolBarView;
    }

    @Override
    public void update() {
        toolBarView.update();
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
        shortcutHandler.handle(KeyCode.DELETE, true);
    }

    @Override
    public void onUndoAction() {
        shortcutHandler.handle(KeyCode.Z, true);
    }
}
