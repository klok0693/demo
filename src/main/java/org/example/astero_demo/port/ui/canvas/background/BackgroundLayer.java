package org.example.astero_demo.port.ui.canvas.background;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;

public class BackgroundLayer extends CanvasLayer {

    public BackgroundLayer(GraphicsContext gc) {
        super(gc, 0);
        add(new CanvasBackgroundDrawable());
    }
}
