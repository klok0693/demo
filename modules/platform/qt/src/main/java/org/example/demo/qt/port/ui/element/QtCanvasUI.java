package org.example.demo.qt.port.ui.element;

import org.example.demo.core.port.ui.canvas.CanvasLayer;
import org.example.demo.core.port.ui.canvas.CanvasUI;
import org.example.demo.qt.port.ui.canvas.background.QtBackgroundLayer;
import org.example.demo.qt.port.ui.canvas.shape.QtShapeLayer;
import org.example.demo.qt.port.ui.canvas.tool.QtToolLayer;
import org.example.demo.qt.port.ui.graphics.QtPainter;

import java.util.List;

/**
 * Main(and the only=) canvas
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtCanvasUI implements CanvasUI {

    protected final List<CanvasLayer<QtPainter, ?>> layers;

    public QtCanvasUI(
            final QtBackgroundLayer backgroundLayer,
            final QtShapeLayer shapeLayer,
            final QtToolLayer toolLayer) {
        this.layers = List.of(backgroundLayer, shapeLayer, toolLayer);
    }

    @Override
    public void redraw() {
        layers.stream().sorted().forEach(layer -> layer.draw(new QtPainter(/*getGraphicsContext2D()*/)));
    }
}
