package org.example.astero_demo.port.ui.canvas.background;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.port.ui.canvas.CanvasElement;

class CanvasBackgroundElement extends CanvasElement {

    protected CanvasBackgroundElement() {
        super(10, 10, 710, 620);
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        final int cellWidth = 25;

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(x, y, width, height);

        gc.setFill(Color.WHITE);
        for (int i = (int) x; i < width; i += cellWidth) {
            int startJ = (int) y;
            if ((i / cellWidth) % 2 != 0) {
                startJ = cellWidth + (int) y;
            }
            for (int j = startJ; j < height; j += (cellWidth << 1)) {
                gc.fillRect(i, j, cellWidth, cellWidth);
            }
        }
    }
}
