package org.example.astero_demo.swing.port.ui.root;

import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.swing.port.ui.element.SwingCanvasUI;
import org.example.astero_demo.swing.port.ui.layers.SwingLayersPanelUI;
import org.example.astero_demo.swing.port.ui.properties.SwingPropertiesPanelUI;
import org.example.astero_demo.swing.port.ui.toolbar.SwingToolBarUI;

import javax.swing.*;
import java.awt.*;

/**
 * UI part of {@link SwingRootView}
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class SwingRootUI extends JPanel implements RootUI {
    private final RootView view;

    public SwingRootUI(
            final SwingLayersPanelUI layersUI,
            final SwingPropertiesPanelUI propertiesPanelUI,
            final SwingToolBarUI toolBarUI,
            final SwingCanvasUI canvas,
            final RootView rootView) {
        this.view = rootView;

        setLayout(new BorderLayout());

        add(canvas, BorderLayout.CENTER);
        add(toolBarUI, BorderLayout.NORTH);
        add(layersUI, BorderLayout.WEST);
        add(propertiesPanelUI, BorderLayout.EAST);
    }

    @Override
    public void setSwingCursor(final Cursor cursor) {
        setCursor(cursor);
    }
}
