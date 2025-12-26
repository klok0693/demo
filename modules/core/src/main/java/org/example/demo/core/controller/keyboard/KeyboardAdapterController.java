package org.example.demo.core.controller.keyboard;

import org.example.demo.core.adapter.ui.CursorLocator;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.logic.ClipboardProcessor;
import org.example.demo.core.logic.ShapeProcessor;

import java.util.Optional;

public class KeyboardAdapterController implements KeyboardController {
    private final ShapeProcessor shapeProcessor;
    private final ClipboardProcessor clipboardProcessor;
    private final CursorLocator cursorLocator;
    private final ModelState state;

    public KeyboardAdapterController(
            final ShapeProcessor shapeProcessor,
            final ClipboardProcessor clipboardProcessor,
            final CursorLocator cursorLocator,
            final ModelState state) {
        this.shapeProcessor = shapeProcessor;
        this.clipboardProcessor = clipboardProcessor;
        this.cursorLocator = cursorLocator;
        this.state = state;
    }

    @Override
    public void removeShape(final int id) {
        shapeProcessor.removeShape(id);
    }

    @Override
    public void undoLastOperation() {
        shapeProcessor.undoLastOperation();
    }

    @Override
    public void cut(final int obj) {
        clipboardProcessor.cut(obj);
    }

    @Override
    public void copy(final int id) {
        clipboardProcessor.copy(state.getShape(id));
    }

    @Override
    public void paste() {
        final Optional<double[]> currentPosition = cursorLocator.getLocalCursorPosition();
        currentPosition.ifPresent(pos -> clipboardProcessor.paste(pos[0], pos[1]));
    }
}
