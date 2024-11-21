package org.example.astero_demo.adapter.keyboard;

import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.event.CopyShapeEvent;
import org.example.astero_demo.adapter.ui.event.PasteShapeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.logic.event.ui.UndoLastOperationEvent;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class EditorOperationAdapter implements OperationAdapter {
    private final LogicEventProcessor processor;
    private final UIState state;
    private final ParentAdapter parentAdapter;

    public EditorOperationAdapter(
            final LogicEventProcessor processor,
            final UIState state,
            final ParentAdapter adapter) {
        this.processor = processor;
        this.state = state;
        this.parentAdapter = adapter;
    }

    @Override
    public void handleDelete() {
        if (state.hasSelectedId()) {
            processor.process(new RemoveShapeEvent(state.getSelectedShapeId()));
        }
    }

    @Override
    public void handleUndo() {
        processor.process(new UndoLastOperationEvent());
    }

    @Override
    public void handleCopy() {
        if (state.hasSelectedId()) {
            parentAdapter.processEvent(new CopyShapeEvent());
        }
    }

    @Override
    public void handlePaste() {
        if (state.hasCopy()) {
            parentAdapter.processEvent(new PasteShapeEvent());
        }
    }
}
