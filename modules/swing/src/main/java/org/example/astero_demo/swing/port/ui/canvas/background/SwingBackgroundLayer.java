package org.example.astero_demo.swing.port.ui.canvas.background;

import org.example.astero_demo.core.port.ui.canvas.background.BackgroundLayer;

import java.awt.*;

/**
 * JavaFX's realization of {@link BackgroundLayer}. Necessary, because it<p>
 * hold a link to {@link GraphicsContext}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingBackgroundLayer extends BackgroundLayer<Graphics> {

    public SwingBackgroundLayer() {
        super();
        add(new SwingBackgroundTilesElement());
    }
}
