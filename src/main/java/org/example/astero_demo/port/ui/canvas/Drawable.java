package org.example.astero_demo.port.ui.canvas;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable {

    void draw(GraphicsContext gc);

    void destroyLinks();
}
