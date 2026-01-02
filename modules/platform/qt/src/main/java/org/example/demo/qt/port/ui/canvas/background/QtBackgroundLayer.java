package org.example.demo.qt.port.ui.canvas.background;

import org.example.demo.core.port.ui.canvas.background.BackgroundLayer;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * JavaFX's realization of {@link BackgroundLayer}. Necessary, because it<p>
 * hold a link to {@link GraphicsContext}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtBackgroundLayer extends BackgroundLayer<QtPainter> {

    public QtBackgroundLayer() {
        super(new QtBackgroundTilesElement());
    }
}
