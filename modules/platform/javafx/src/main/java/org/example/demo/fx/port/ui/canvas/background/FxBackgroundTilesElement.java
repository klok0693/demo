package org.example.demo.fx.port.ui.canvas.background;

import org.example.demo.core.port.ui.canvas.background.BackgroundTilesElement;
import org.example.demo.fx.port.ui.graphics.FxPainter;

/**
 * Background element, splited all work area on squares, make navigation easier
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class FxBackgroundTilesElement extends BackgroundTilesElement<FxPainter> {

    protected FxBackgroundTilesElement() {
        super(0.0, 0.0, 710, 620);
    }
}
