package org.example.astero_demo.fx.port.ui.canvas.background;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.core.port.ui.canvas.background.BackgroundLayer;

/**
 * JavaFX's realization of {@link BackgroundLayer}. Necessary, because it<p>
 * hold a link to {@link GraphicsContext}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxBackgroundLayer extends BackgroundLayer<GraphicsContext> {

    public FxBackgroundLayer() {
        super();
        add(new FxBackgroundTilesElement());
    }
}
