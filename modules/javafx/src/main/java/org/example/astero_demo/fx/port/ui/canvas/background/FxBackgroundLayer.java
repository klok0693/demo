package org.example.astero_demo.fx.port.ui.canvas.background;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.core.port.ui.canvas.background.BackgroundLayer;

public class FxBackgroundLayer extends BackgroundLayer<GraphicsContext> {

    public FxBackgroundLayer() {
        super();
        add(new FxBackgroundTilesElement());
    }
}
