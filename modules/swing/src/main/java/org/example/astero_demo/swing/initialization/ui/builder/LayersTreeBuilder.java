package org.example.astero_demo.swing.initialization.ui.builder;

import javafx.util.Builder;
import org.example.astero_demo.core.port.ui.elements.LayersTree;
import org.example.astero_demo.swing.port.ui.element.SwingLayersTree;

import java.util.HashMap;

import static java.lang.Boolean.parseBoolean;

/**
 * {@link Builder} for {@link LayersTree}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class LayersTreeBuilder extends HashMap<String, String> implements Builder<SwingLayersTree> {
    private final SwingLayersTree layersTree;

    public LayersTreeBuilder(final SwingLayersTree layersTree) {
        this.layersTree = layersTree;
    }

    @Override
    public SwingLayersTree build() {
        layersTree.setShowRoot(parseBoolean(get("showRoot")));
        return layersTree;
    }
}
