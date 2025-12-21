package org.example.astero_demo.swing.port.ui.root;

import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.swing.port.ui.element.SwingCanvasUI;
import org.example.astero_demo.swing.port.ui.properties.SwingPropertiesPanelUI;
import org.example.astero_demo.swing.port.ui.toolbar.SwingToolBarUI;

import javax.swing.*;
import java.awt.*;

public class SwingRootUI extends JPanel implements RootUI {
    private final RootView view;

    public SwingRootUI(
            final SwingPropertiesPanelUI propertiesPanelUI,
            final SwingToolBarUI toolBarUI,
            final SwingCanvasUI canvas,
            final RootView rootView) {
        this.view = rootView;

        setLayout(new BorderLayout());

        add(canvas, BorderLayout.CENTER);
        add(toolBarUI, BorderLayout.NORTH);
        add(new Button("placeholder"), BorderLayout.WEST);
        //add(new Button("placeholder"), BorderLayout.SOUTH);
        add(propertiesPanelUI, BorderLayout.EAST);
    }

    @Override
    public void setSwingCursor(final Cursor cursor) {
        setCursor(cursor);
    }
}
