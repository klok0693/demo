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
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtCanvasUI implements CanvasUI, QtMemoryView {
    //<editor-fold desc="ABI method's names">
    private static final String NATIVE_REF_NAME = "ui_canvas_get";
    private static final String NATIVE_SET_DRAWING_CALLBACK_NAME = "set_drawing_callback";
    private static final String NATIVE_UPDATE_CANVAS_ITEM_NAME = "update_canvas_item";
    //</editor-fold>

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
                "paintComponent",
                void.class,
                new Class[]{ MemorySegment.class },
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
                NATIVE_REF_NAME,
                NATIVE_SET_DRAWING_CALLBACK_NAME
        );

        this.getQtRefHandle =
                findNative(NATIVE_REF_NAME, FunctionDescriptor.of(ValueLayout.ADDRESS));

        this.redrawHandle =
                findNative(
                        NATIVE_UPDATE_CANVAS_ITEM_NAME,
                        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
    }

    @Override
    @SneakyThrows
    public void redraw() {
        redrawHandle.invoke(getQtRefHandle.invoke());
    }

    public void paintComponent(final MemorySegment ctxPtr) {
        layers.stream().sorted().forEach(layer -> layer.draw(QtPainterFactory.build(ctxPtr)));
    }
}
