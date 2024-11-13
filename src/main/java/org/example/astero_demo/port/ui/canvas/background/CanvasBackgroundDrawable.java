package org.example.astero_demo.port.ui.canvas.background;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.port.ui.canvas.Drawable;

class CanvasBackgroundDrawable implements Drawable {

    @Override
    public void draw(final GraphicsContext gc) {
        final int border = 10;
        final int width = 630;
        final int height = 380;
        final int cellWidth = 25;

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(border, border, width, height);

        gc.setFill(Color.WHITE);
        for (int i = border; i < width; i += cellWidth) {
            int startJ = border;
            if ((i / cellWidth) % 2 != 0) {
                startJ = cellWidth + border;
            }
            for (int j = startJ; j < height; j += (cellWidth << 1)) {
                gc.fillRect(i, j, cellWidth, cellWidth);
            }
        }
    }

    @Override
    public void destroyLinks() {}
}
