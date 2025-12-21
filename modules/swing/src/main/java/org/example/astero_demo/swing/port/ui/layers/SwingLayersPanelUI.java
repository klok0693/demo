package org.example.astero_demo.swing.port.ui.layers;

import org.example.astero_demo.swing.port.ui.element.SwingLayersTree;
import org.example.astero_demo.swing.util.SwingConstants;

import javax.swing.*;
import java.awt.*;

/**
 * UI part of {@link SwingLayersPanelView}
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class SwingLayersPanelUI extends Box implements LayersPanelUI {

    public SwingLayersPanelUI(final SwingLayersTree layersTree) {
        super(BoxLayout.Y_AXIS);

        //setBackground(Color.green);
        //setOpaque(true);
        setEnabled(false);
        setBorder(SwingConstants.VIEW_BORDER);

        setPreferredSize(new Dimension(240, 380));
        setAlignmentY(JComponent.TOP_ALIGNMENT);

        final JScrollPane scroll = new JScrollPane(layersTree);
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, scroll.getPreferredSize().height));

        add(scroll);
    }
}
