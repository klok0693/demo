package org.example.demo.fx.port.ui.canvas.tool.draggable.selection;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ShapeSelectionTool;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.demo.fx.port.ui.canvas.FxCanvasElement;
import org.example.demo.fx.port.ui.graphics.FxPainter;

import java.util.HashMap;
import java.util.Map;

/**
 * JavaFX's realization of {@link ShapeSelectionTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxShapeSelectionTool extends ShapeSelectionTool<FxPainter> implements FxCanvasElement {

    private final Map<Integer, FxModificableSelectionFrame> frames = new HashMap<>(2);

    public FxShapeSelectionTool(
            final ModelState modelState,
            final UIState uiState,
            final CanvasAdapter adapter) {
        super(modelState, uiState, adapter);
    }

    @Override
    protected ModificableSelectionFrame<FxPainter> createModificableFrame(
            final CanvasAdapter adapter,
            final UIState uiState) {
        return new FxModificableSelectionFrame(adapter, uiState);
    }

    @Override
    public void draw(final FxPainter gc) {
        save(gc);
        super.draw(gc);
        restore(gc);
    }
}
