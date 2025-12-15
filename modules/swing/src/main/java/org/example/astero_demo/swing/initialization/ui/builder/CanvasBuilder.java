package org.example.astero_demo.swing.initialization.ui.builder;

import javafx.util.Builder;
import org.example.astero_demo.swing.port.ui.element.SwingCanvas;

import java.util.HashMap;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

/**
 * {@link Builder} for {@link org.example.astero_demo.adapter.ui.canvas.CanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CanvasBuilder extends HashMap<String, String> implements Builder<SwingCanvas> {
    private final SwingCanvas canvas;

    public CanvasBuilder(final SwingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public SwingCanvas build() {
        canvas.setWidth(parseDouble(get("width")));
        canvas.setHeight(parseDouble(get("height")));
        canvas.setFocusTraversable(parseBoolean(get("focusTraversable")));
        return canvas;
    }
}
