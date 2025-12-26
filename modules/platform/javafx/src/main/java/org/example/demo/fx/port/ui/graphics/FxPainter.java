package org.example.demo.fx.port.ui.graphics;

import javafx.scene.canvas.GraphicsContext;
import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.api.graphics.color.Color;

public class FxPainter implements GraphicsPainter {
    private final GraphicsContext gc;

    public FxPainter(final GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void setFill(final Color color) {
        gc.setFill(javafx.scene.paint.Color.color(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                color.getAlpha()));
    }

    @Override
    public void strokeRect(final double x, final double y, final double width, final double height) {
        gc.strokeRect(x, y, width, height);
    }

    @Override
    public void fillRect(final double x, final double y, final double width, final double height) {
        gc.fillRect(x, y, width, height);
    }

    @Override
    public void fillOval(final double x, final double y, final double width, final double height) {
        gc.fillOval(x, y, width, height);
    }

    @Override
    public void setOpacity(final double opacity) {
        gc.setGlobalAlpha(opacity);
    }

    @Override
    public void setStroke(final Color color) {
        gc.setStroke(javafx.scene.paint.Color.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
    }

    @Override
    public void setLineWidth(final double width) {
        gc.setLineWidth(width);
    }

    public void save() {
        gc.save();
    }

    public void restore() {
        gc.restore();
    }
}
