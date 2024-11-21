package org.example.astero_demo.port.ui.canvas.background;

import org.example.astero_demo.port.ui.canvas.CanvasLayer;

/**
 * Layer for any background element, making working with canvas more convenient
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class BackgroundLayer extends CanvasLayer<CanvasBackgroundElement> {

    public BackgroundLayer() {
        super(0);
        add(new CanvasBackgroundElement());
    }
}
