package org.example.astero_demo.fx.port.ui.canvas.background;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.api.graphics.GraphicsPainter;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.core.port.ui.canvas.background.BackgroundTilesElement;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;
import org.example.astero_demo.fx.port.ui.graphics.FxPainter;

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
    public void draw(FxPainter gc) {
        save(gc);
        super.draw(gc);
        restore(gc);
    }

    @Override
    public void save(final FxPainter gc) {
        FxCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final FxPainter gc) {
        FxCanvasElement.super.restore(gc);
    }
}
