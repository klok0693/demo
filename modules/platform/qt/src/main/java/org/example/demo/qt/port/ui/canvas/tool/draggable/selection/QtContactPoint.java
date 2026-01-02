package org.example.demo.qt.port.ui.canvas.tool.draggable.selection;

import org.example.demo.api.graphics.color.Color;
import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.SelectionFrame;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.demo.qt.port.ui.canvas.QtCanvasElement;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * JavaFX's realization of {@link ContactPoint}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtContactPoint extends ContactPoint<QtPainter> implements QtCanvasElement {

    public QtContactPoint(
            final SelectionFrame<QtPainter> selectionTool,
            final CanvasAdapter adapter,
            final int layer,
            final Color fillColor,
            final ContactAlignment alignment) {
        super(selectionTool, adapter, layer, alignment, fillColor);
    }

    @Override
    public void draw(final QtPainter gc) {
        save(gc);
        super.draw(gc);
        restore(gc);
    }
}
