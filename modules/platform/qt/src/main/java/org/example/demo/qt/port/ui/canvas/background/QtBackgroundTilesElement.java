package org.example.demo.qt.port.ui.canvas.background;

import org.example.demo.core.port.ui.canvas.background.BackgroundTilesElement;
import org.example.demo.qt.port.ui.canvas.QtCanvasElement;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * Background element, splited all work area on squares, make navigation easier
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class QtBackgroundTilesElement extends BackgroundTilesElement<QtPainter> implements QtCanvasElement {

    protected QtBackgroundTilesElement() {
        super(0.0, 0.0, 710, 620);
    }
}
