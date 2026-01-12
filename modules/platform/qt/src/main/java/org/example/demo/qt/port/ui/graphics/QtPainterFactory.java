package org.example.demo.qt.port.ui.graphics;

import org.example.demo.qt.port.ui.QtMemoryView;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

/**
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtPainterFactory implements QtMemoryView {
    //<editor-fold desc="ABI method's names">
    private static final String NATIVE_PAINTER_SAVE_NAME = "ui_painter_save";
    private static final String NATIVE_PAINTER_RESTORE_NAME = "ui_painter_restore";
    private static final String NATIVE_PAINTER_SET_FILL_NAME = "ui_painter_set_fill";
    private static final String NATIVE_PAINTER_FILL_RECT_NAME = "ui_painter_fill_rect";
    private static final String NATIVE_PAINTER_STROKE_RECT_NAME = "ui_painter_stroke_rect";
    private static final String NATIVE_PAINTER_FILL_OVAL_NAME = "ui_painter_fill_oval";
    private static final String NATIVE_PAINTER_SET_OPACITY_NAME = "ui_painter_set_opacity";
    private static final String NATIVE_PAINTER_SET_STROKE_NAME = "ui_painter_set_stroke";
    private static final String NATIVE_PAINTER_SET_LINE_WIDTH_NAME = "ui_painter_set_line_width";
    //</editor-fold>

    private static MethodHandle saveHandle;
    private static MethodHandle restoreSegment;
    private static MethodHandle setFillHandle;
    private static MethodHandle fillRectHandle;
    private static MethodHandle setStrokeRectHandle;
    private static MethodHandle fillOvalHandle;
    private static MethodHandle setOpacityHandle;
    private static MethodHandle setStrokeHandle;
    private static MethodHandle setLineWidthHandle;

    public static QtPainter build(final MemorySegment segment) {
        return new QtPainter(
                segment,
                saveHandle,
                restoreSegment,
                setFillHandle,
                fillRectHandle,
                setStrokeRectHandle,
                fillOvalHandle,
                setOpacityHandle,
                setStrokeHandle,
                setLineWidthHandle);
    }

    @Override
    public void initialize() throws Throwable {
        this.saveHandle =
                findNative(
                        NATIVE_PAINTER_SAVE_NAME,
                        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));

        this.restoreSegment =
                findNative(
                        NATIVE_PAINTER_RESTORE_NAME,
                        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));

        this.setFillHandle =
                findNative(
                        NATIVE_PAINTER_SET_FILL_NAME,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.ADDRESS
                        ));

        this.fillRectHandle =
                findNative(
                        NATIVE_PAINTER_FILL_RECT_NAME,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE
                        ));

        this.setStrokeRectHandle =
                findNative(
                        NATIVE_PAINTER_STROKE_RECT_NAME,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE
                        ));


        this.fillOvalHandle =
                findNative(
                        NATIVE_PAINTER_FILL_OVAL_NAME,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE,
                                ValueLayout.JAVA_DOUBLE
                        ));

        this.setOpacityHandle =
                findNative(
                        NATIVE_PAINTER_SET_OPACITY_NAME,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE
                        ));

        this.setStrokeHandle =
                findNative(
                        NATIVE_PAINTER_SET_STROKE_NAME,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.ADDRESS
                        ));

        this.setLineWidthHandle =
                findNative(
                        NATIVE_PAINTER_SET_LINE_WIDTH_NAME,
                        FunctionDescriptor.ofVoid(
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_DOUBLE
                        ));
    }
}
