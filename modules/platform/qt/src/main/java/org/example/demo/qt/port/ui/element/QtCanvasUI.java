package org.example.demo.qt.port.ui.element;

import lombok.SneakyThrows;
import org.example.demo.core.port.ui.canvas.CanvasLayer;
import org.example.demo.core.port.ui.canvas.CanvasUI;
import org.example.demo.qt.port.ui.QtMemoryView;
import org.example.demo.qt.port.ui.canvas.background.QtBackgroundLayer;
import org.example.demo.qt.port.ui.canvas.shape.QtShapeLayer;
import org.example.demo.qt.port.ui.canvas.tool.QtToolLayer;
import org.example.demo.qt.port.ui.graphics.QtPainter;
import org.example.demo.qt.port.ui.graphics.QtPainterFactory;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.List;

/**
 * Main(and the only=) canvas
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtCanvasUI implements CanvasUI, QtMemoryView {
    private static final String NATIVE_REF_NAME = "ui_canvas_get";

    private MemorySegment onDrawSegment;
    private MethodHandle getQtRefHandle;
    private MethodHandle redrawHandle;

    protected final List<CanvasLayer<QtPainter, ?>> layers;

    public QtCanvasUI(
            final QtBackgroundLayer backgroundLayer,
            final QtShapeLayer shapeLayer,
            final QtToolLayer toolLayer) {
        this.layers = List.of(backgroundLayer, shapeLayer, toolLayer);
    }

    @Override
    public void initialize() throws Throwable {
        this.onDrawSegment = bindMethodToNative(
                /*"redraw"*/"paintComponent",
                void.class,
                new Class[]{ MemorySegment.class },
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
                "ui_canvas_get",
                "setDrawingCallback"
        );

        this.getQtRefHandle =
                findNative(NATIVE_REF_NAME, FunctionDescriptor.of(ValueLayout.ADDRESS));

        this.redrawHandle =
                findNative(
                        "updateCanvasItem",
                        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
    }

    @Override
    @SneakyThrows
    public void redraw() {
        System.out.println("redraw");
        redrawHandle.invoke(getQtRefHandle.invoke());
    }

    public void paintComponent(final MemorySegment ctxPtr) {
        System.out.println("paint components layers" + layers.size());
        layers.stream().sorted().forEach(layer -> layer.draw(QtPainterFactory.build(ctxPtr)));
    }
}
