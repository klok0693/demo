package org.example.astero_demo.swing.port.ui;

import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.LayersPanelView;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;
import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.core.port.ui.ToolBarView;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.core.port.ui.model.Cursors;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX's realization of {@link RootView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingRootView extends RootView {
    //private final EventHandler shortcutHandler;

    public ToolBarView toolBarRootController;
    public ShapeCanvasView canvasRootController;
    public PropertiesPanelView propertyRootController;
    public LayersPanelView layersRootController;
    //public BorderPane root;

    public SwingRootView(
            //final EventHandler shortcutHandler,
            final UIState uiState) {
        super(uiState);
        //this.shortcutHandler = shortcutHandler;
    }

/*    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        // TODO: Bind on scene, not root view
        root.setOnKeyPressed(shortcutHandler::handle);
    }*/

    @Override
    protected void setCursor(final Cursors cursor) {
/*        final Cursor fxCursor = switch (cursor) {
            case DEFAULT -> Cursor.DEFAULT;
            case CROSSHAIR -> Cursor.CROSSHAIR;
        };
        root.setCursor(fxCursor);*/
    }
}
