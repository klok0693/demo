package org.example.astero_demo.port.ui;

import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.port.ui.elements.LayersTree;

public class LayersPanelView implements UpdatableView {
    public LayersTree layersTree;

    @Override
    public void update() {
        layersTree.update();
    }
}
