package org.example.astero_demo.fx.initialization.ui.builder;

import javafx.util.Builder;
import org.example.astero_demo.core.port.ui.elements.LayersTree;
import org.example.astero_demo.fx.port.ui.element.FxLayersTree;

import java.util.HashMap;

import static java.lang.Boolean.parseBoolean;

/**
 * {@link Builder} for {@link LayersTree}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class LayersTreeBuilder extends HashMap<String, String> implements Builder<FxLayersTree> {
    private final FxLayersTree layersTree;

    public LayersTreeBuilder(final FxLayersTree layersTree) {
        this.layersTree = layersTree;
    }

    @Override
    public FxLayersTree build() {
        layersTree.setShowRoot(parseBoolean(get("showRoot")));
        return layersTree;
    }
}
