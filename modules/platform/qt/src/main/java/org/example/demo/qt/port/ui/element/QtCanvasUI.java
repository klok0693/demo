package org.example.demo.qt.port.ui.element;

import org.example.demo.core.port.ui.canvas.CanvasLayer;
import org.example.demo.core.port.ui.canvas.CanvasUI;
import org.example.demo.qt.port.ui.QtMemoryView;
import org.example.demo.qt.port.ui.canvas.background.QtBackgroundLayer;
import org.example.demo.qt.port.ui.canvas.shape.QtShapeLayer;
import org.example.demo.qt.port.ui.canvas.tool.QtToolLayer;
import org.example.demo.qt.port.ui.graphics.QtPainter;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.util.List;

/**
 * Main(and the only=) canvas
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtCanvasUI implements CanvasUI, QtMemoryView {
    private MemorySegment onDrawSegment;

    protected final List<CanvasLayer<QtPainter, ?>> layers;

    public QtCanvasUI(
            final QtBackgroundLayer backgroundLayer,
            final QtShapeLayer shapeLayer,
            final QtToolLayer toolLayer) {
        this.layers = List.of(backgroundLayer, shapeLayer, toolLayer);
    }

    @Override
    public void initialize() throws Throwable {
        onDrawSegment = bindMethodToNative(
                "redraw",
                void.class,
                new Class[]{},
                FunctionDescriptor.ofVoid(),
                "ui_canvas_get",
                "setDrawingCallback"
        );
    }

    @Override
    public void redraw() {
        System.out.println("redraw");
        layers.stream().sorted().forEach(layer -> layer.draw(new QtPainter(/*getGraphicsContext2D()*/)));
    }
}
