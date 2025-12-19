package org.example.astero_demo.core.port.ui.canvas.background;

import org.example.astero_demo.api.graphics.GraphicsPainter;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.api.graphics.color.Colors;

/**
 * Background element, splited all work area on squares, make navigation easier
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class BackgroundTilesElement<E extends GraphicsPainter> extends CanvasElement<E> {
    private static final int CELL_SIDE = 25;
    private static final int PADDING = 10;

    protected BackgroundTilesElement(
            final double x,
            final double y,
            final double width,
            final double height) {
        super(x, y, width, height);
    }

    @Override
    protected void drawElement(final E gc) {
        // Fill all canvas with background color
        gc.setFill(Colors.LIGHTBLUE);
        gc.fillRect(x, y, width + PADDING, height + PADDING);

        // Fill canvas with padding with light grey
        gc.setFill(Colors.LIGHTGRAY);
        gc.fillRect(x + PADDING, y + PADDING, width - PADDING, height - PADDING);

        // Fill grey area with white cells
        gc.setFill(Colors.WHITE);
        for (int i = (int) x + PADDING; i < width; i += CELL_SIDE) {
            int startJ = (int) y + PADDING;
            if ((i / CELL_SIDE) % 2 != 0) {
                startJ = CELL_SIDE + (int) y + PADDING;
            }
            for (int j = startJ; j < height; j += (CELL_SIDE << 1)) {
                // Last cell can extend beyond the canvas. To prevent this
                // needs manually cut last cell's dimensions
                final double currentX = i + CELL_SIDE - width;
                double calcWidth = CELL_SIDE;
                if (currentX > 0.0) {
                    calcWidth -= currentX;
                }
                final double currentY = j + CELL_SIDE - height;
                double calcHeight = CELL_SIDE;
                if (currentY > 0.0) {
                    calcHeight -= currentY;
                }
                gc.fillRect(i, j, calcWidth, calcHeight);
            }
        }
    }
}
