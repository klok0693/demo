package org.example.demo.qt.port.ui.canvas.tool.draggable.selection;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ShapeSelectionTool;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.demo.qt.port.ui.canvas.QtCanvasElement;
import org.example.demo.qt.port.ui.graphics.QtPainter;

import java.util.HashMap;
import java.util.Map;

/**
 * JavaFX's realization of {@link ShapeSelectionTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtShapeSelectionTool extends ShapeSelectionTool<QtPainter> implements QtCanvasElement {

    private final Map<Integer, QtModificableSelectionFrame> frames = new HashMap<>(2);

    public QtShapeSelectionTool(
            final ModelState modelState,
            final UIState uiState,
            final CanvasAdapter adapter) {
        super(modelState, uiState, adapter);
    }

    @Override
    protected ModificableSelectionFrame<QtPainter> createModificableFrame(
            final CanvasAdapter adapter,
            final UIState uiState) {
        return new QtModificableSelectionFrame(adapter, uiState);
    }

    @Override
    public void draw(final QtPainter gc) {
        save(gc);
        super.draw(gc);
        restore(gc);
    }
}
