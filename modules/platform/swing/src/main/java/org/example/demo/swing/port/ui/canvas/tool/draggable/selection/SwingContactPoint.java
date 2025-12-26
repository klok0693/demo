package org.example.demo.swing.port.ui.canvas.tool.draggable.selection;

import org.example.demo.api.graphics.color.Color;
import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.SelectionFrame;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.demo.swing.port.ui.graphics.SwingPainter;

/**
 * Swing realization of {@link ContactPoint}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingContactPoint extends ContactPoint<SwingPainter> implements SwingCanvasElement {

    public SwingContactPoint(
            final SelectionFrame<SwingPainter> selectionTool,
            final CanvasAdapter adapter,
            final int layer,
            final Color fillColor,
            final ContactAlignment alignment) {
        super(selectionTool, adapter, layer, alignment, fillColor);
    }
}
