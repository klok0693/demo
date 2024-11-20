package org.example.astero_demo.port.ui;

import javafx.scene.layout.VBox;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.port.ui.elements.LayersTree;

public class LayersPanel extends VBox implements UpdatableView {
    public final LayersTree layersTree;

    public LayersPanel(final LayersTree layersTree) {
        this.layersTree = layersTree;
    }

    @Override
    public void update() {
        layersTree.update();
    }
}
