package org.example.astero_demo.core.port.ui.canvas.background;

import org.example.astero_demo.api.graphics.GraphicsPainter;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.core.port.ui.canvas.CanvasLayer;

/**
 * Layer for any background element, making working with canvas more convenient
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class BackgroundLayer<E extends GraphicsPainter> extends CanvasLayer<E, CanvasElement<E>> {

    protected BackgroundLayer(final CanvasElement<E> backgroundTilesElement) {
        super(0);
        add(backgroundTilesElement);
    }
}
