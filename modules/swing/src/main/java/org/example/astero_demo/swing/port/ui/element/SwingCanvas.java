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

    public SwingCanvas() {
        setBackground(Color.CYAN);
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (drawindConsumer != null) {
            drawindConsumer.accept(g);
        }
    }
}
