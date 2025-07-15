package org.example.astero_demo.fx.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.core.port.ui.canvas.CanvasLayer;

public class FxCanvasLayer extends CanvasLayer<GraphicsContext, FxCanvasElement> {

    public FxCanvasLayer(final int priority) {
        super(priority);
    }
}
