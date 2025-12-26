package org.example.demo.core.adapter.keyboard;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.controller.keyboard.KeyboardController;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class EditorOperationAdapter implements OperationAdapter {
    private final KeyboardController controller;
    private final UIState state;

    public EditorOperationAdapter(final KeyboardController controller, final UIState state) {
        this.state = state;
        this.controller = controller;
    }

    @Override
    public void handleDelete() {
        if (state.hasSelectedId() && !state.isMultipleSelection()) {
            controller.removeShape(state.getSelectedShapeId());
        }
    }

    @Override
    public void handleUndo() {
        controller.undoLastOperation();
    }

    @Override
    public void handleCopy() {
        if (state.hasSelectedId()) {
            controller.copy(state.getSelectedShapeId());
        }
    }

    @Override
    public void handleCut() {
        if (state.hasSelectedId()) {
            controller.cut(state.getSelectedShapeId());
        }
    }

    @Override
    public void handlePaste() {
        controller.paste();
    }
}
