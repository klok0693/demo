package org.example.astero_demo.port.ui;

import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.port.ui.elements.LayersTree;

/**
 * Represents a view for displaying and updating layers in a tree format.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class LayersPanelView implements UpdatableView {
    public LayersTree layersTree;

    @Override
    public void update() {
        layersTree.update();
    }
}
