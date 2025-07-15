package org.example.astero_demo.fx.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.core.port.ui.canvas.Drawable;

public interface FxDrawable extends Drawable<GraphicsContext> {

    @Override
    void draw(GraphicsContext gc);
}
