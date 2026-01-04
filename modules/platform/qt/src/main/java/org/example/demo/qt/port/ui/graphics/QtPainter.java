package org.example.demo.qt.port.ui.graphics;

import lombok.SneakyThrows;
import org.example.demo.api.graphics.GraphicsPainter;
import org.example.demo.api.graphics.color.Color;
import org.example.demo.qt.port.ui.QtMemoryView;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

public class QtPainter implements GraphicsPainter {
    private final MemorySegment ctxPtr;

    private final MethodHandle setFillSegment;
    private final MethodHandle fillRectSegment;

    public QtPainter(
            final MemorySegment ctxPtr,
            final MethodHandle setFillSegment,
            final MethodHandle fillRectSegment) {
        this.ctxPtr = ctxPtr;
        this.setFillSegment = setFillSegment;
        this.fillRectSegment = fillRectSegment;
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
    public void strokeRect(final double x, final double y, final double width, final double height) {
        //gc.strokeRect(x, y, width, height);
    }

    @Override
    @SneakyThrows
    public void fillRect(final double x, final double y, final double width, final double height) {
        fillRectSegment.invoke(ctxPtr, x, y, width, height);
    }

    @Override
    public void fillOval(final double x, final double y, final double width, final double height) {
        //gc.fillOval(x, y, width, height);
    }

    @Override
    public void setOpacity(final double opacity) {
        //gc.setGlobalAlpha(opacity);
    }

    @Override
    public void setStroke(final Color color) {
        //gc.setStroke(javafx.scene.paint.Color.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
    }

    @Override
    public void setLineWidth(final double width) {
        //gc.setLineWidth(width);
    }
}
