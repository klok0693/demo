package org.example.astero_demo.realization.initialization.ui.builder;

import javafx.scene.canvas.Canvas;
import javafx.util.Builder;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;

import java.util.HashMap;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

/**
 * {@link Builder} for {@link org.example.astero_demo.adapter.ui.canvas.CanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CanvasBuilder extends HashMap<String, String> implements Builder<Canvas> {
    private final ShapeCanvasView canvasView;

    public CanvasBuilder(final ShapeCanvasView canvasView) {
        this.canvasView = canvasView;
    }

    @Override
    public Canvas build() {
        canvasView.setWidth(parseDouble(get("width")));
        canvasView.setHeight(parseDouble(get("height")));
        canvasView.setFocusTraversable(parseBoolean(get("focusTraversable")));
        return canvasView;
    }
}
