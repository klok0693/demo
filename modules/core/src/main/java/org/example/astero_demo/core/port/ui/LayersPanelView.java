package org.example.astero_demo.core.port.ui;

import org.example.astero_demo.core.adapter.ui.UpdatableView;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersView;
import org.example.astero_demo.core.adapter.ui.state.mode.InsertModeSwitchable;
import org.example.astero_demo.core.port.ui.elements.LayersTree;

/**
 * Represents a view for displaying and updating layers in a tree format.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class LayersPanelView implements LayersView {
    public LayersTree layersTree;

    @Override
    public void update() {
        layersTree.update();
    }

    @Override
    public void switchToInsertMode() {
        layersTree.unSelectAll();
    }

    @Override
    public void switchToSingleSelectionMode() {}

    @Override
    public void switchToMultipleSelectionMode() {}
}
