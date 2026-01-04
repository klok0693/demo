package org.example.demo.qt.port.ui.graphics;

import org.example.demo.qt.port.ui.QtMemoryView;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

public class QtPainterFactory implements QtMemoryView {
    private static MethodHandle setFillSegment;
    private static MethodHandle fillRectSegment;

    public static QtPainter build(final MemorySegment segment) {
        return new QtPainter(segment, setFillSegment, fillRectSegment);
    }

    @Override
    public void initialize() throws Throwable {
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
    }
}
