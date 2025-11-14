package org.example.astero_demo.fx.port.ui.canvas.tool.draggable.selection;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.SelectionFrame;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;

import static org.example.astero_demo.core.port.ui.UIConstants.CONTACT_DIAMETER;

/**
 * JavaFX's realization of {@link ContactPoint}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxContactPoint extends ContactPoint<GraphicsContext> implements FxCanvasElement {
    protected Color fillColor;

    public FxContactPoint(
            final SelectionFrame<GraphicsContext> selectionTool,
            final CanvasAdapter adapter,
            final int layer,
            final Color fillColor,
            final ContactAlignment alignment) {
        super(selectionTool, adapter, layer, alignment);
        this.fillColor = fillColor;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x - RADIUS, y - RADIUS, CONTACT_DIAMETER, CONTACT_DIAMETER);
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
