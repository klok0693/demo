package org.example.astero_demo.fx.initialization.ui.builder;

import javafx.util.Builder;
import org.example.astero_demo.fx.port.ui.element.FxCanvasUI;

import java.util.HashMap;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

/**
 * {@link Builder} for {@link org.example.astero_demo.adapter.ui.canvas.CanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CanvasBuilder extends HashMap<String, String> implements Builder<FxCanvasUI> {
    private final FxCanvasUI canvas;

    public CanvasBuilder(final FxCanvasUI canvas) {
        this.canvas = canvas;
    }

    @Override
    public FxCanvasUI build() {
        canvas.setWidth(parseDouble(get("width")));
        canvas.setHeight(parseDouble(get("height")));
        canvas.setFocusTraversable(parseBoolean(get("focusTraversable")));
        return canvas;
    }
}
