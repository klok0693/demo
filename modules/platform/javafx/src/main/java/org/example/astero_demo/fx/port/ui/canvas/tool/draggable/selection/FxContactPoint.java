package org.example.astero_demo.fx.port.ui.canvas.tool.draggable.selection;

import org.example.astero_demo.api.graphics.color.Color;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.SelectionFrame;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;
import org.example.astero_demo.fx.port.ui.graphics.FxPainter;

/**
 * JavaFX's realization of {@link ContactPoint}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxContactPoint extends ContactPoint<FxPainter> implements FxCanvasElement {

    public FxContactPoint(
            final SelectionFrame<FxPainter> selectionTool,
            final CanvasAdapter adapter,
            final int layer,
            final Color fillColor,
            final ContactAlignment alignment) {
        super(selectionTool, adapter, layer, alignment, fillColor);
    }

    @Override
    public void draw(final FxPainter gc) {
        save(gc);
        super.draw(gc);
        restore(gc);
    }
}
