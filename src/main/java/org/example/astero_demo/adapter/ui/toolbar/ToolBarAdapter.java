package org.example.astero_demo.adapter.ui.toolbar;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.event.InsertModeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.port.ui.ToolBarView;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolBarAdapter extends LeafAdapter implements OperationProcessor {

    private final RootShortcutHandler shortcutHandler; // TODO: remove
    private final ToolBarView toolBarView;

    public ToolBarAdapter(
            final ViewController controller,
            final UIState uiState,
            final RootShortcutHandler shortcutHandler,
            final ToolBarView toolBarView) {
        super(controller, uiState);
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
