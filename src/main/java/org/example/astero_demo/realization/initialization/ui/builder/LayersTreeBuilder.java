package org.example.astero_demo.realization.initialization.ui.builder;

import javafx.util.Builder;
import org.example.astero_demo.port.ui.elements.LayersTree;

import java.util.HashMap;

import static java.lang.Boolean.parseBoolean;

public class LayersTreeBuilder extends HashMap<String, String> implements Builder<LayersTree> {
    private final LayersTree layersTree;

    public LayersTreeBuilder(final LayersTree layersTree) {
        this.layersTree = layersTree;
    }

    @Override
    public LayersTree build() {
        layersTree.setShowRoot(parseBoolean(get("showRoot")));
        return layersTree;
    }
}
