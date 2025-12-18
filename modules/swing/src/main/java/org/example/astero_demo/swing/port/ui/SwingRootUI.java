package org.example.astero_demo.swing.port.ui;

import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.swing.port.ui.toolbar.SwingToolBarUI;

import javax.swing.*;
import java.awt.*;

public class SwingRootUI extends JPanel {
    private final RootView view;

    public SwingRootUI(
            final SwingToolBarUI toolBarUI,
            final RootView rootView) {
        this.view = rootView;

        setLayout(new BorderLayout());

        add(new Button("placeholder"), BorderLayout.CENTER);
        add(toolBarUI, BorderLayout.NORTH);
        add(new Button("placeholder"), BorderLayout.WEST);
        add(new Button("placeholder"), BorderLayout.SOUTH);
        add(new Button("placeholder"), BorderLayout.EAST);
    }
}
