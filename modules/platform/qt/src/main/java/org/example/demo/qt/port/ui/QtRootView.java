package org.example.demo.qt.port.ui;

import lombok.SneakyThrows;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.demo.core.port.ui.LayersPanelView;
import org.example.demo.core.port.ui.PropertiesPanelView;
import org.example.demo.core.port.ui.RootView;
import org.example.demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.demo.core.port.ui.model.Cursors;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

/**
 * JavaFX's realization of {@link RootView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtRootView extends RootView implements QtMemoryView {

    public ToolBarView toolBarRootController;
    public ShapeCanvasView canvasRootController;
    public PropertiesPanelView propertyRootController;
    public LayersPanelView layersRootController;

    private MethodHandle setCursorHandle;

    public QtRootView(final UIState uiState) {
        super(uiState);
    }

    @Override
    public void initialize() throws Throwable {
        this.setCursorHandle =
                findNative(
                        "setCursor",
                        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
                );

        System.out.println("set cursor handle");
    }

    @Override
    @SneakyThrows
    protected void setCursor(final Cursors cursor) {
        System.out.println("change cursor");
        try (final Arena arena = Arena.ofConfined()) {
            final MemorySegment utf8 = arena.allocateUtf8String(cursor.toString());
            setCursorHandle.invoke(utf8);
        }
    }
}
