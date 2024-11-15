package org.example.astero_demo.adapter.keyboard;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.logic.event.ui.UndoLastOperationEvent;

public class RootShortcutHandler implements EventHandler<KeyEvent> {
    private final LogicEventProcessor processor;
    private final UIState state;

    public RootShortcutHandler(final LogicEventProcessor processor, final UIState state) {
        this.processor = processor;
        this.state = state;
    }

    @Override
    public void handle(final KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case DELETE:
                if (state.hasSelectedId()) {
                    processor.process(new RemoveShapeEvent(state.getSelectedShapeId()));
                }
                break;
            case Z:
                if (keyEvent.isControlDown()) {
                    processor.process(new UndoLastOperationEvent());
                }
                break;
        }
    }
}
