package org.example.demo.qt.port.ui.graphics;

import org.example.demo.qt.port.ui.QtMemoryView;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

public class QtPainterFactory implements QtMemoryView {
    private static MethodHandle saveSegment;
    private static MethodHandle restoreSegment;
    private static MethodHandle setFillSegment;
    private static MethodHandle fillRectSegment;
    private static MethodHandle setStrokeRectSegment;
    private static MethodHandle fillOvalSegment;
    private static MethodHandle setOpacitySegment;
    private static MethodHandle setStrokeSegment;
    private static MethodHandle setLineWidthSegment;

    public static QtPainter build(final MemorySegment segment) {
        return new QtPainter(
                segment,
                saveSegment,
                restoreSegment,
                setFillSegment,
                fillRectSegment,
                setStrokeRectSegment,
                fillOvalSegment,
                setOpacitySegment,
                setStrokeSegment,
                setLineWidthSegment);
    }

    @Override
    public void initialize() throws Throwable {
        this.saveSegment =
                findNative(
                        "ui_painter_save",
                        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));

        this.restoreSegment =
                findNative(
                        "ui_painter_restore",
                        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));

        this.setFillSegment =
                findNative(
                        "ui_painter_set_fill",
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.ADDRESS
                        ));

        this.fillRectSegment =
                findNative(
                        "ui_painter_fill_rect",
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE
                        ));

        this.setStrokeRectSegment =
                findNative(
                        "ui_painter_stroke_rect",
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE
                        ));


        this.fillOvalSegment =
                findNative(
                        "ui_painter_fill_oval",
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE
                        ));

        this.setOpacitySegment =
                findNative(
                        "ui_painter_set_opacity",
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE
                        ));

        this.setStrokeSegment =
                findNative(
                        "ui_painter_set_stroke",
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.ADDRESS
                        ));

        this.setLineWidthSegment =
                findNative(
                        "ui_painter_set_line_width",
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE
                        ));
    }
}
