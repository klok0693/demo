package org.example.astero_demo.fx.port.ui.canvas.tool.draggable.selection;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ModificableSelectionFrame;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;

public class FxModificableSelectionFrame extends ModificableSelectionFrame<GraphicsContext>
        implements FxCanvasElement {

    private static final double FRAME_WIDTH = 3.0;
    protected final Color fillColor;

    public FxModificableSelectionFrame(final CanvasAdapter adapter, final UIState uiState) {
        super(adapter, uiState);
        this.fillColor = Color.RED;
    }

    @Override
    protected ContactPoint<GraphicsContext> createPoint(
            final CanvasAdapter adapter,
            final int layer,
            final ContactAlignment alignment) {
        return new FxContactPoint(this, adapter, layer, fillColor, alignment);
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setStroke(fillColor);
        gc.setLineWidth(FRAME_WIDTH);
        gc.strokeRect(x, y, width, height);

        contactPoints.forEach(point -> point.draw(gc));
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
