package org.example.astero_demo.fx.initialization.ui.builder;

import javafx.util.Builder;
import org.example.astero_demo.fx.port.ui.element.FxCanvasUI;

/**
 * {@link Builder} for {@link org.example.astero_demo.adapter.ui.canvas.CanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CanvasBuilder extends NodeIdBuilder<FxCanvasUI> {
    private final FxCanvasUI canvas;

    public CanvasBuilder(final FxCanvasUI canvas) {
        this.canvas = canvas;
    }

    @Override
    protected FxCanvasUI buildNode() {
        return canvas;
    }

    @Override
    protected void setUpNode(final FxCanvasUI node) {
        node.setWidth(getDouble("width"));
        node.setHeight(getDouble("height"));
        node.setFocusTraversable(getBoolean("focusTraversable"));
    }
}
