package org.example.astero_demo.swing.port.ui.canvas.background;

import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.swing.port.ui.canvas.SwingCanvasElement;

import java.awt.*;

/**
 * Background element, splited all work area on squares, make navigation easier
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class SwingBackgroundTilesElement extends CanvasElement<Graphics> implements SwingCanvasElement {
    private static final int CELL_SIDE = 25;
    private static final int PADDING = 10;

    protected SwingBackgroundTilesElement() {
        super(0.0, 0.0, 710, 620);
    }

    @Override
    protected void drawElement(final Graphics gc) {
        gc.setColor(Color.BLUE);
        gc.fillRect((int) x, (int) y, (int) (width + PADDING), (int) (height + PADDING));

/*        // Fill all canvas with background color
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(x, y, width + PADDING, height + PADDING);

        // Fill canvas with padding with light grey
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(x + PADDING, y + PADDING, width - PADDING, height - PADDING);

        // Fill grey area with white cells
        gc.setFill(Color.WHITE);
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
        }*/
    }

    @Override
    public void save(final Graphics gc) {
        SwingCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final Graphics gc) {
        SwingCanvasElement.super.restore(gc);
    }
}
