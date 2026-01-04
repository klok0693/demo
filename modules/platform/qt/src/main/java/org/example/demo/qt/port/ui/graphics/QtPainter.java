package org.example.demo.qt.port.ui.graphics;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.api.graphics.color.Color;
import org.example.demo.qt.port.ui.QtMemoryView;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

@AllArgsConstructor
public class QtPainter implements GraphicsPainter {
    private final MemorySegment ctxPtr;

    private final MethodHandle saveSegment;
    private final MethodHandle restoreSegment;
    private final MethodHandle setFillSegment;
    private final MethodHandle fillRectSegment;
    private final MethodHandle setStrokeRectSegment;
    private final MethodHandle fillOvalSegment;
    private final MethodHandle setOpacitySegment;
    private final MethodHandle setStrokeSegment;
    private final MethodHandle setLineWidthSegment;

    @Override
    @SneakyThrows
    public void save() {
        saveSegment.invoke(ctxPtr);
    }

    @Override
    @SneakyThrows
    public void restore() {
        restoreSegment.invoke(ctxPtr);
    }

    @Override
    @SneakyThrows
    public void setFill(final Color color) {
        try (Arena arena = Arena.ofConfined()) {
            final MemorySegment utf8 = arena.allocateUtf8String(color.toString());
            setFillSegment.invoke(ctxPtr, utf8);
        }
    }

    @Override
    @SneakyThrows
    public void strokeRect(final double x, final double y, final double width, final double height) {
        setStrokeRectSegment.invoke(ctxPtr, x, y, width, height);
    }

    @Override
    @SneakyThrows
    public void fillRect(final double x, final double y, final double width, final double height) {
        fillRectSegment.invoke(ctxPtr, x, y, width, height);
    }

    @Override
    @SneakyThrows
    public void fillOval(final double x, final double y, final double width, final double height) {
        fillOvalSegment.invoke(ctxPtr, x, y, width, height);
    }

    @Override
    @SneakyThrows
    public void setOpacity(final double opacity) {
        setOpacitySegment.invoke(ctxPtr, opacity);
    }

    @Override
    @SneakyThrows
    public void setStroke(final Color color) {
        try (Arena arena = Arena.ofConfined()) {
            final MemorySegment utf8 = arena.allocateUtf8String(color.toString());
            setStrokeSegment.invoke(ctxPtr, utf8);
        }
    }

    @Override
    @SneakyThrows
    public void setLineWidth(final double width) {
        setLineWidthSegment.invoke(ctxPtr, width);
    }
}
