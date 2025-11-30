package org.example.astero_demo.fx.port.ui.canvas.tool.draggable.selection;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ShapeSelectionTool;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;

import java.util.HashMap;
import java.util.Map;

/**
 * JavaFX's realization of {@link ShapeSelectionTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxShapeSelectionTool extends ShapeSelectionTool<GraphicsContext> implements FxCanvasElement {

    private final Map<Integer, FxModificableSelectionFrame> frames = new HashMap<>(2);

    public FxShapeSelectionTool(
            final ModelState modelState,
            final UIState uiState,
            final CanvasAdapter adapter) {
        super(modelState, uiState, adapter);
    }

    @Override
    protected ModificableSelectionFrame<GraphicsContext> createModificableFrame(
            final CanvasAdapter adapter,
            final UIState uiState) {
        return new FxModificableSelectionFrame(adapter, uiState);
    }

    @Override
    public void save(final GraphicsContext gc) {
        FxCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final GraphicsContext gc) {
        FxCanvasElement.super.restore(gc);
    }
}
