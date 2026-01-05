package org.example.demo.qt.port.ui.canvas.tool.draggable.selection;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.demo.qt.port.ui.canvas.QtCanvasElement;
import org.example.demo.qt.port.ui.graphics.QtPainter;

public class QtModificableSelectionFrame extends ModificableSelectionFrame<QtPainter>
        implements QtCanvasElement {

    public QtModificableSelectionFrame(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
    }

    @Override
    protected ContactPoint<QtPainter> createPoint(
            final CanvasAdapter adapter,
            final int layer,
            final ContactAlignment alignment) {
        return new QtContactPoint(this, adapter, layer, fillColor, alignment);
    }
}
