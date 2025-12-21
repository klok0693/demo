package org.example.astero_demo.fx.port.ui;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.astero_demo.core.port.ui.LayersPanelView;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;
import org.example.astero_demo.core.port.ui.RootView;
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
public class FxRootView extends RootView {

    public ToolBarView toolBarRootController;
    public ShapeCanvasView canvasRootController;
    public PropertiesPanelView propertyRootController;
    public LayersPanelView layersRootController;
    public BorderPane root;

    public FxRootView(final UIState uiState) {
        super(uiState);
    }

    @Override
    protected void setCursor(final Cursors cursor) {
        final Cursor fxCursor = switch (cursor) {
            case DEFAULT -> Cursor.DEFAULT;
            case CROSSHAIR -> Cursor.CROSSHAIR;
        };
        root.setCursor(fxCursor);
    }
}
