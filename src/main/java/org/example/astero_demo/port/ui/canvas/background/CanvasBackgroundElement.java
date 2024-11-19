package org.example.astero_demo.port.ui.canvas.background;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.port.ui.canvas.CanvasElement;

class CanvasBackgroundElement extends CanvasElement {
    private static final int CELL_SIDE = 25;
    private static final int PADDING = 10;

    protected CanvasBackgroundElement() {
        super(PADDING, PADDING, 710, 620);
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(x, y, width, height);

        gc.setFill(Color.WHITE);
        for (int i = (int) x; i < width; i += CELL_SIDE) {
            int startJ = (int) y;
            if ((i / CELL_SIDE) % 2 != 0) {
                startJ = CELL_SIDE + (int) y;
            }
            for (int j = startJ; j < height; j += (CELL_SIDE << 1)) {
                gc.fillRect(i, j, CELL_SIDE, CELL_SIDE);
            }
        }
    }
}
