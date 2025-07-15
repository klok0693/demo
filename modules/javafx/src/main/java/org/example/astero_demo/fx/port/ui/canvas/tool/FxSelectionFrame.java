package org.example.astero_demo.fx.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.port.ui.canvas.tool.SelectionFrame;

import static org.example.astero_demo.core.port.ui.UIConsrants.MINIMAL_SIDE_SIZE;

/**
 * JavaFX's realization of {@link SelectionFrame}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxSelectionFrame extends FxCanvasTool implements SelectionFrame<GraphicsContext> {
    private static final double FRAME_WIDTH = 3.0;
    protected final Color fillColor;

    public FxSelectionFrame() {
        super(-1, -1, -1, -1, 0);
        this.fillColor = Color.RED;
        setEnabled(true);
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setStroke(fillColor);
        gc.setLineWidth(FRAME_WIDTH);
        gc.strokeRect(x, y, width, height);
    }

    @Override
    public void update(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = Math.max(width, MINIMAL_SIDE_SIZE);
        this.height = Math.max(height, MINIMAL_SIDE_SIZE);
        setVisible(true);
    }

    @Override
    public boolean isInBounds(final double x, final double y) {
        return x >= this.x && x <= this.x + this.width
                && y >= this.y && y <= this.y + this.height;
    }
}
