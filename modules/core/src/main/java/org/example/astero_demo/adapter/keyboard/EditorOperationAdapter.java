package org.example.astero_demo.adapter.keyboard;

import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.event.CopyShapeEvent;
import org.example.astero_demo.adapter.ui.event.PasteShapeEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.logic.ShapeProcessor;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class EditorOperationAdapter implements OperationAdapter {
    private final ShapeProcessor processor;
    private final UIState state;
    private final ParentAdapter parentAdapter;

    public EditorOperationAdapter(
            final ShapeProcessor processor,
            final UIState state,
            final ParentAdapter adapter) {
        this.processor = processor;
        this.state = state;
        this.parentAdapter = adapter;
    }

    @Override
    public void handleDelete() {
        if (state.hasSelectedId() && !state.isMultipleSelection()) {
            processor.removeShape(state.getSelectedShapeId());
        }
    }

    @Override
    public void handleUndo() {
        processor.undoLastOperation();
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
