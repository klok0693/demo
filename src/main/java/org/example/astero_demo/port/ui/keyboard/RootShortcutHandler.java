package org.example.astero_demo.port.ui.keyboard;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Setter;
import org.example.astero_demo.adapter.keyboard.KeyBoardAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.event.CopyShapeEvent;
import org.example.astero_demo.adapter.ui.event.PasteShapeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.logic.event.ui.UndoLastOperationEvent;

public class RootShortcutHandler implements EventHandler<KeyEvent> {
    private final KeyBoardAdapter keyBoardAdapter;

    public RootShortcutHandler(final KeyBoardAdapter keyBoardAdapter) {
        this.keyBoardAdapter = keyBoardAdapter;
    }

    @Override
    public void handle(final KeyEvent keyEvent) {
        handle(keyEvent.getCode(), keyEvent.isControlDown());
    }

    public void handle(final KeyCode keyCode, final boolean isCtrlDown) {
        switch (keyCode) {
            case DELETE:
                keyBoardAdapter.handleDelete();
                break;
            case Z:
                if (isCtrlDown) {
                    keyBoardAdapter.handleUndo();
                }
                break;
            case C:
                if (isCtrlDown) {
                    keyBoardAdapter.handleCopy();
                }
                break;
            case V:
                if (isCtrlDown) {
                    keyBoardAdapter.handlePaste();
                }
                break;
        }
    }
}
