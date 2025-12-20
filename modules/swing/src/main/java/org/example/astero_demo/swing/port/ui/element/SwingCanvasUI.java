package org.example.astero_demo.swing.port.ui.element;

import lombok.Setter;
import org.example.astero_demo.core.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.core.port.ui.canvas.CanvasUI;
import org.example.astero_demo.swing.port.ui.canvas.background.SwingBackgroundLayer;
import org.example.astero_demo.swing.port.ui.canvas.shape.SwingShapeLayer;
import org.example.astero_demo.swing.port.ui.canvas.tool.SwingToolLayer;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Main(and the only=) canvas
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingCanvasUI extends JComponent/*Canvas*/ implements CanvasUI {
    @Setter
    private Consumer<Graphics> drawindConsumer;

    protected final List<CanvasLayer<SwingPainter, ?>> layers;

    public SwingCanvasUI(
            final SwingBackgroundLayer backgroundLayer,
            final SwingShapeLayer shapeLayer,
            final SwingToolLayer toolLayer) {

        this.layers = List.of(backgroundLayer, shapeLayer, toolLayer);
        setBackground(Color.CYAN);
    }

    private double[] mock = new double[] {0.0, 0.0};

    public void mock(double x, double y) {
        mock[0] = x;
        mock[1] = y;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
/*        g.setColor(Color.BLACK);
        g.fillRect((int) mock[0], (int) mock[1], 30, 30);*/
        if (drawindConsumer != null) {
            drawindConsumer.accept(g);
        }
        layers.stream().sorted().forEach(layer -> layer.draw(new SwingPainter(g)));
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void redraw() {
        repaint();
    }
}
