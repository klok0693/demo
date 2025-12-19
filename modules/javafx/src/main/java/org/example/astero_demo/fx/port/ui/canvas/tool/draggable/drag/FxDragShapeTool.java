package org.example.astero_demo.fx.port.ui.canvas.tool.draggable.drag;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.fx.port.ui.graphics.FxPainter;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.drag.DragShapeTool;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;

/**
 * JavaFX's realization of {@link DragShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxDragShapeTool extends DragShapeTool<FxPainter> implements FxCanvasElement {

    public FxDragShapeTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        super(adapter, modelState, uiState);
    }

    @Override
    public void draw(final FxPainter gc) {
        save(gc);
        super.draw(gc);
        restore(gc);
    }

    @Override
    public void save(final FxPainter gc) {
        FxCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final FxPainter gc) {
        FxCanvasElement.super.restore(gc);
    }
}
