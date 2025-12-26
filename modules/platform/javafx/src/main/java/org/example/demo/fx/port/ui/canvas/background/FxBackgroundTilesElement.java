package org.example.demo.fx.port.ui.canvas.background;

import org.example.demo.core.port.ui.canvas.background.BackgroundTilesElement;
import org.example.demo.fx.port.ui.canvas.FxCanvasElement;
import org.example.demo.fx.port.ui.graphics.FxPainter;

/**
 * Background element, splited all work area on squares, make navigation easier
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class FxBackgroundTilesElement extends BackgroundTilesElement<FxPainter> implements FxCanvasElement {

    protected FxBackgroundTilesElement() {
        super(0.0, 0.0, 710, 620);
    }

    @Override
    public void draw(final FxPainter gc) {
        save(gc);
        super.draw(gc);
        restore(gc);
    }
}
