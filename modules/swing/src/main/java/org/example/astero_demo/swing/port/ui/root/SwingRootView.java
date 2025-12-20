package org.example.astero_demo.swing.port.ui.root;

import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.astero_demo.core.port.ui.LayersPanelView;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;
import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.core.port.ui.model.Cursors;

import java.awt.*;

/**
 * Swing realization of {@link RootView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingRootView extends RootView {
    //private final EventHandler shortcutHandler;

    public ToolBarView toolBarRootController;
    public ShapeCanvasView canvasView;
    public PropertiesPanelView propertyRootController;
    public LayersPanelView layersRootController;

    public RootUI root;

    public SwingRootView(
            //final EventHandler shortcutHandler,
            final ShapeCanvasView canvasView,
            final UIState uiState,
            final RootUI root) {
        super(uiState);
        this.canvasView = canvasView;
        this.root = root;
        //this.shortcutHandler = shortcutHandler;
    }

/*    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        // TODO: Bind on scene, not root view
        root.setOnKeyPressed(shortcutHandler::handle);
    }*/

    @Override
    protected void setCursor(final Cursors cursor) {
        final Cursor swingCursor = switch (cursor) {
            case DEFAULT -> Cursor.getDefaultCursor();
            case CROSSHAIR -> Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
        };
        root.setSwingCursor(swingCursor);
    }
}
