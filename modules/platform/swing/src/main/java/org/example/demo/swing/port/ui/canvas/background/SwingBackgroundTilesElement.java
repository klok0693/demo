package org.example.demo.swing.port.ui.canvas.background;

import org.example.demo.core.port.ui.canvas.background.BackgroundTilesElement;
import org.example.demo.swing.port.ui.canvas.SwingCanvasElement;
import org.example.demo.swing.port.ui.graphics.SwingPainter;

/**
 * Background element, splited all work area on squares, make navigation easier
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
class SwingBackgroundTilesElement extends BackgroundTilesElement<SwingPainter> implements SwingCanvasElement {

    protected SwingBackgroundTilesElement() {
        super(0.0, 0.0, 710, 620);
    }
}
