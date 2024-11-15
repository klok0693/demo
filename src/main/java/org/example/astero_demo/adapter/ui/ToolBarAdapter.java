package org.example.astero_demo.adapter.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.adapter.ui.event.InsertModeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.port.ui.ToolBarView;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolBarAdapter extends LeafAdapter {

    private final RootShortcutHandler shortcutHandler; // TODO: remove

    public ToolBarView toolBar;
    public ToggleButton rectBtn;
    public ToggleButton cycleBtn;
    public Button deleteBtn;
    public Button undoBtn;

    public ToolBarAdapter(final ViewController controller, final UIState uiState, final RootShortcutHandler shortcutHandler) {
        super(controller, uiState);
        this.shortcutHandler = shortcutHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void update() {
        final boolean needToUnStickBtn = uiState.isInInsertMode();
        rectBtn.setSelected(needToUnStickBtn);
        cycleBtn.setSelected(needToUnStickBtn);

        final boolean disableRelated = uiState.isInInsertMode() || !uiState.hasSelectedId();
        deleteBtn.setDisable(disableRelated);
    }

    public void onInsertRectAction(final ActionEvent event) {
        sendEvent(new InsertModeEvent(ShapeType.RECT));
    }

    public void onInsertCycleAction(final ActionEvent event) {
        sendEvent(new InsertModeEvent(ShapeType.OVAL));
    }

    public void onDeleteAction(final ActionEvent event) {
        shortcutHandler.handle(KeyCode.DELETE, true);
    }

    public void onUndoAction(final ActionEvent event) {
        shortcutHandler.handle(KeyCode.Z, true);
    }
}
