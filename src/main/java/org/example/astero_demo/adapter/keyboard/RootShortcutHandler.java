package org.example.astero_demo.adapter.keyboard;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Setter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.event.CopyShapeEvent;
import org.example.astero_demo.adapter.ui.event.PasteShapeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.logic.event.ui.UndoLastOperationEvent;

public class RootShortcutHandler implements EventHandler<KeyEvent> {
    private final LogicEventProcessor processor;
    private final UIState state;
    protected ParentAdapter parentAdapter;

    public RootShortcutHandler(final LogicEventProcessor processor, final ParentAdapter parentAdapter, final UIState state) {
        this.processor = processor;
        this.parentAdapter = parentAdapter;
        this.state = state;
    }

    @Override
    public void handle(final KeyEvent keyEvent) {
        handle(keyEvent.getCode(), keyEvent.isControlDown());
    }

    public void handle(final KeyCode keyCode, final boolean isCtrlDown) {
        switch (keyCode) {
            case DELETE:
                if (state.hasSelectedId()) {
                    processor.process(new RemoveShapeEvent(state.getSelectedShapeId()));
                }
                break;
            case Z:
                if (isCtrlDown) {
                    processor.process(new UndoLastOperationEvent());
                }
                break;
            case C:
                if (isCtrlDown && state.hasSelectedId()) {
                    parentAdapter.processEvent(new CopyShapeEvent());
                }
                break;
            case V:
                if (isCtrlDown && state.hasCopy()) {
                    parentAdapter.processEvent(new PasteShapeEvent());
                }
                break;
        }
    }
}
