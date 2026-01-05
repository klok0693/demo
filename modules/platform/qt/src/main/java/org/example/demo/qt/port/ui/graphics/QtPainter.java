package org.example.demo.qt.port.ui.graphics;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.api.graphics.color.Color;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

@AllArgsConstructor
public class QtPainter implements GraphicsPainter {
    private final MemorySegment ctxPtr;

    private final MethodHandle saveHandle;
    private final MethodHandle restoreHandle;
    private final MethodHandle setFillHandle;
    private final MethodHandle fillRectHandle;
    private final MethodHandle setStrokeRectHandle;
    private final MethodHandle fillOvalHandle;
    private final MethodHandle setOpacityHandle;
    private final MethodHandle setStrokeHandle;
    private final MethodHandle setLineWidthHandle;

    @Override
    @SneakyThrows
    public void save() {
        saveHandle.invoke(ctxPtr);
    }

    @Override
    @SneakyThrows
    public void restore() {
        restoreHandle.invoke(ctxPtr);
    }

    @Override
    @SneakyThrows
    public void setFill(final Color color) {
        try (Arena arena = Arena.ofConfined()) {
            final MemorySegment utf8 = arena.allocateUtf8String(color.toString());
            setFillHandle.invoke(ctxPtr, utf8);
        }
    }

    @Override
    @SneakyThrows
    public void strokeRect(final double x, final double y, final double width, final double height) {
        setStrokeRectHandle.invoke(ctxPtr, x, y, width, height);
    }

    @Override
    @SneakyThrows
    public void fillRect(final double x, final double y, final double width, final double height) {
        fillRectHandle.invoke(ctxPtr, x, y, width, height);
    }

    @Override
    @SneakyThrows
    public void fillOval(final double x, final double y, final double width, final double height) {
        fillOvalHandle.invoke(ctxPtr, x, y, width, height);
    }

    @Override
    @SneakyThrows
    public void setOpacity(final double opacity) {
        setOpacityHandle.invoke(ctxPtr, opacity);
    }

    @Override
    @SneakyThrows
    public void setStroke(final Color color) {
        try (Arena arena = Arena.ofConfined()) {
            final MemorySegment utf8 = arena.allocateUtf8String(color.toString());
            setStrokeHandle.invoke(ctxPtr, utf8);
        }
    }

    @Override
    @SneakyThrows
    public void setLineWidth(final double width) {
        setLineWidthHandle.invoke(ctxPtr, width);
    }
}
