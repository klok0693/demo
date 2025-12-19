package org.example.astero_demo.swing.port.ui.canvas.tool.draggable.selection;

import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.SelectionFrame;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactAlignment;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection.ContactPoint;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

import java.awt.*;

import static org.example.astero_demo.core.port.ui.UIConstants.CONTACT_DIAMETER;

/**
 * JavaFX's realization of {@link ContactPoint}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingContactPoint extends ContactPoint<SwingPainter> implements SwingCanvasElement {

    public SwingContactPoint(
            final SelectionFrame<SwingPainter> selectionTool,
            final CanvasAdapter adapter,
            final int layer,
            final Color fillColor,
            final ContactAlignment alignment) {
        super(selectionTool, adapter, layer, alignment,
                org.example.astero_demo.api.graphics.color.Color.rgb(
                        fillColor.getRed(),
                        fillColor.getGreen(),
                        fillColor.getBlue()
                ));
    }
}
