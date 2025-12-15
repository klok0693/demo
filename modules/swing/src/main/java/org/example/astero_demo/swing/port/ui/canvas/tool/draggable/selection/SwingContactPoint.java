package org.example.astero_demo.swing.port.ui.canvas.tool.draggable.selection;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.SelectionFrame;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;

import java.awt.*;

import static org.example.astero_demo.core.port.ui.UIConstants.CONTACT_DIAMETER;

/**
 * JavaFX's realization of {@link ContactPoint}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingContactPoint extends ContactPoint<Graphics> implements SwingCanvasElement {
    protected Color fillColor;

    public SwingContactPoint(
            final SelectionFrame<Graphics> selectionTool,
            final CanvasAdapter adapter,
            final int layer,
            final Color fillColor,
            final ContactAlignment alignment) {
        super(selectionTool, adapter, layer, alignment);
        this.fillColor = fillColor;
    }

    @Override
    protected void drawElement(final Graphics gc) {
 /*       gc.setFill(Color.RED);
        gc.fillOval(x - RADIUS, y - RADIUS, CONTACT_DIAMETER, CONTACT_DIAMETER);*/
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
