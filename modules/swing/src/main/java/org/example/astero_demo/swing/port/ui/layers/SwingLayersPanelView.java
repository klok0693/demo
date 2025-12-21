package org.example.astero_demo.swing.port.ui.layers;

import org.example.astero_demo.core.port.ui.LayersPanelView;
import org.example.astero_demo.core.port.ui.elements.LayersTree;
import org.example.astero_demo.swing.port.ui.toolbar.SwingToolBarView;

/**
 * Swing realization of {@link LayersPanelView}
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class SwingLayersPanelView extends LayersPanelView {

    public SwingLayersPanelView(final LayersTree layersTree) {
        super(layersTree);
    }
}
