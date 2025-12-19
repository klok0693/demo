package org.example.astero_demo.swing.port.ui.canvas.background;

import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.core.port.ui.canvas.background.BackgroundTilesElement;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

import java.awt.*;

/**
 * Background element, splited all work area on squares, make navigation easier
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class SwingBackgroundTilesElement extends BackgroundTilesElement<SwingPainter> implements SwingCanvasElement {

    protected SwingBackgroundTilesElement() {
        super(0.0, 0.0, 710, 620);
    }
}
