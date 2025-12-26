package org.example.demo.swing.port.ui.canvas.tool.draggable.selection;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.demo.swing.port.ui.graphics.SwingPainter;

/**
 * Swing realization of {@link ModificableSelectionFrame}. Necessary, because it<p>
 * hold a link to {@link SwingPainter}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingModificableSelectionFrame extends ModificableSelectionFrame<SwingPainter>
        implements SwingCanvasElement {

    public SwingModificableSelectionFrame(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
    }

    @Override
    protected ContactPoint<SwingPainter> createPoint(
            final CanvasAdapter adapter,
            final int layer,
            final ContactAlignment alignment) {
        return new SwingContactPoint(this, adapter, layer, fillColor, alignment);
    }
}
