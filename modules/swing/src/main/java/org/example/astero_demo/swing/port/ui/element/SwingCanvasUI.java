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
    protected final List<CanvasLayer<SwingPainter, ?>> layers;

    public SwingCanvasUI(
            final SwingBackgroundLayer backgroundLayer,
            final SwingShapeLayer shapeLayer,
            final SwingToolLayer toolLayer) {

        this.layers = List.of(backgroundLayer, shapeLayer, toolLayer);
        setBackground(Color.CYAN);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        layers.stream().sorted().forEach(layer -> layer.draw(new SwingPainter(g)));

        // Some canvas tools require fast canvas update
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void redraw() {
        repaint();
    }
}
