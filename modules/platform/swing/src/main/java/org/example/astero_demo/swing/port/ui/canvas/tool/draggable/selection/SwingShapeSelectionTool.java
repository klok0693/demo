package org.example.astero_demo.swing.port.ui.canvas.tool.draggable.selection;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ShapeSelectionTool;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

import java.util.HashMap;
import java.util.Map;

/**
 * Swing realization of {@link ShapeSelectionTool}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingShapeSelectionTool extends ShapeSelectionTool<SwingPainter> implements SwingCanvasElement {

    private final Map<Integer, SwingModificableSelectionFrame> frames = new HashMap<>(2);

    public SwingShapeSelectionTool(
            final ModelState modelState,
            final UIState uiState,
            final CanvasAdapter adapter) {
        super(modelState, uiState, adapter);
    }

    @Override
    protected ModificableSelectionFrame<SwingPainter> createModificableFrame(
            final CanvasAdapter adapter,
            final UIState uiState) {
        return new SwingModificableSelectionFrame(adapter, uiState);
    }
}
