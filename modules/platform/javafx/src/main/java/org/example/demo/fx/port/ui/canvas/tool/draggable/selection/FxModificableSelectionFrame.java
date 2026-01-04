package org.example.demo.fx.port.ui.canvas.tool.draggable.selection;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.demo.fx.port.ui.graphics.FxPainter;

public class FxModificableSelectionFrame extends ModificableSelectionFrame<FxPainter> {

    public FxModificableSelectionFrame(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
    }

    @Override
    protected ContactPoint<FxPainter> createPoint(
            final CanvasAdapter adapter,
            final int layer,
            final ContactAlignment alignment) {
        return new FxContactPoint(this, adapter, layer, fillColor, alignment);
    }
}
