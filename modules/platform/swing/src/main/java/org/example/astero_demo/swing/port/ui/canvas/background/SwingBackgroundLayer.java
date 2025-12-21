package org.example.astero_demo.swing.port.ui.canvas.background;

import org.example.astero_demo.core.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

/**
 * Swing realization of {@link BackgroundLayer}. Necessary, because it<p>
 * hold a link to {@link SwingPainter}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingBackgroundLayer extends BackgroundLayer<SwingPainter> {

    public SwingBackgroundLayer() {
        super(new SwingBackgroundTilesElement());
    }
}
