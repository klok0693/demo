package org.example.demo.qt.port.ui.canvas.background;

import org.example.demo.core.port.ui.canvas.background.BackgroundLayer;
import org.example.demo.qt.port.ui.graphics.QtPainter;

/**
 * Qt's realization of {@link BackgroundLayer}. Necessary, because it<p>
 * hold a link to {@link QtPainter}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtBackgroundLayer extends BackgroundLayer<QtPainter> {

    public QtBackgroundLayer() {
        super(new QtBackgroundTilesElement());
    }
}
