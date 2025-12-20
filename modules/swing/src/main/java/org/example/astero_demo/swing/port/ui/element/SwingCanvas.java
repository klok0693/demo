package org.example.astero_demo.swing.port.ui.element;

import lombok.Setter;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.function.Consumer;

/**
 * Main(and the only=) canvas
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingCanvas extends JComponent/*Canvas*/ {
    @Setter
    private Consumer<Graphics> drawindConsumer;

    private double[] mock = new double[] {0.0, 0.0};

    public SwingCanvas() {
        setBackground(Color.CYAN);
        setOpaque(true);
    }

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
        Toolkit.getDefaultToolkit().sync();
    }
}
