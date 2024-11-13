package org.example.astero_demo.port.ui.canvas.background;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;

public class BackgroundLayer extends CanvasLayer<CanvasBackgroundElement> {

    public BackgroundLayer(final GraphicsContext gc) {
        super(gc, 0);
        add(new CanvasBackgroundElement());
    }
}
