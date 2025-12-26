package org.example.astero_demo.fx.initialization.ui.builder;

import javafx.util.Builder;
import org.example.astero_demo.core.port.ui.elements.LayersTree;
import org.example.astero_demo.fx.port.ui.element.FxLayersTree;

/**
 * {@link Builder} for {@link LayersTree}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class LayersTreeBuilder extends NodeIdBuilder<FxLayersTree> {
    private final FxLayersTree layersTree;

    public LayersTreeBuilder(final FxLayersTree layersTree) {
        this.layersTree = layersTree;
    }

    @Override
    protected FxLayersTree buildNode() {
        return layersTree;
    }

    @Override
    protected void setUpNode(final FxLayersTree node) {
        layersTree.setShowRoot(getBoolean("showRoot"));
    }
}
