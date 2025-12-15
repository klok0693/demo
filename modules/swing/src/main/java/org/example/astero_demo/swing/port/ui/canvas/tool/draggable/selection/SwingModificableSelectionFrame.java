package org.example.astero_demo.swing.port.ui.canvas.tool.draggable.selection;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;

import java.awt.*;

public class SwingModificableSelectionFrame extends ModificableSelectionFrame<Graphics>
        implements SwingCanvasElement {

    private static final double FRAME_WIDTH = 3.0;
    protected final Color fillColor;

    public SwingModificableSelectionFrame(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
        this.fillColor = Color.RED;
    }

    @Override
    protected ContactPoint<Graphics> createPoint(
            final CanvasAdapter adapter,
            final int layer,
            final ContactAlignment alignment) {
        return new SwingContactPoint(this, adapter, layer, fillColor, alignment);
    }

    @Override
    protected void drawElement(final Graphics gc) {
/*        gc.setStroke(fillColor);
        gc.setLineWidth(FRAME_WIDTH);
        gc.strokeRect(x, y, width, height);*/

        contactPoints.forEach(point -> point.draw(gc));
    }

    @Override
    public void save(final Graphics gc) {
        SwingCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final Graphics gc) {
        SwingCanvasElement.super.restore(gc);
    }
}
