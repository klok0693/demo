package org.example.astero_demo.swing.port.ui.graphics;

import org.example.astero_demo.api.graphics.GraphicsPainter;
import org.example.astero_demo.api.graphics.color.Color;

import java.awt.*;

public class SwingPainter implements GraphicsPainter {
    private final Graphics2D gc;

    public SwingPainter(final Graphics gc) {
        this.gc = (Graphics2D) gc;
    }

    @Override
    public void setFill(final Color color) {
        gc.setColor(new java.awt.Color(
                (float) color.getRed(),
                (float) color.getGreen(),
                (float) color.getBlue(),
                (float) color.getAlpha()));
    }

    @Override
    public void strokeRect(final double x, final double y, final double width, final double height) {
        gc.drawRect((int) x, (int) y, (int) width, (int) height);
    }

    @Override
    public void fillRect(final double x, final double y, final double width, final double height) {
        gc.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    @Override
    public void fillOval(final double x, final double y, final double width, final double height) {
        gc.fillOval((int) x, (int) y, (int) width, (int) height);
    }

    @Override
    public void setOpacity(final double opacity) {
        gc.setComposite(
                AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER,
                        (float) opacity
                )
        );
    }

    @Override
    public void setStroke(final Color color) {
        gc.setPaint(
                new java.awt.Color(
                        (float) color.getRed(),
                        (float) color.getGreen(),
                        (float) color.getBlue(),
                        (float) color.getAlpha()
                )
        );
    }

    @Override
    public void setLineWidth(final double width) {
        gc.setStroke(new BasicStroke(
                (float) width,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER
        ));
    }
}
