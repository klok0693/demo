package org.example.demo.swing.port.ui.root;

import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.demo.core.port.ui.LayersPanelView;
import org.example.demo.core.port.ui.PropertiesPanelView;
import org.example.demo.core.port.ui.RootView;
import org.example.demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.demo.core.port.ui.model.Cursors;

import java.awt.*;

/**
 * Swing realization of {@link RootView}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingRootView extends RootView {
    public ToolBarView toolBarRootController;
    public ShapeCanvasView canvasView;
    public PropertiesPanelView propertyRootController;
    public LayersPanelView layersRootController;

    public RootUI root;

    public SwingRootView(
            final ShapeCanvasView canvasView,
            final UIState uiState,
            final RootUI root) {
        super(uiState);
        this.canvasView = canvasView;
        this.root = root;
    }

    @Override
    protected void setCursor(final Cursors cursor) {
        final Cursor swingCursor = switch (cursor) {
            case DEFAULT -> Cursor.getDefaultCursor();
            case CROSSHAIR -> Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
        };
        root.setSwingCursor(swingCursor);
    }
}
