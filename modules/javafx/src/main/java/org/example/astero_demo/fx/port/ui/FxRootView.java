package org.example.astero_demo.fx.port.ui;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.LayersPanelView;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;
import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.core.port.ui.ToolBarView;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX's realization of {@link RootView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxRootView extends RootView implements Initializable {
    private final EventHandler shortcutHandler;

    public ToolBarView toolBarRootController;
    public ShapeCanvasView canvasRootController;
    public PropertiesPanelView propertyRootController;
    public LayersPanelView layersRootController;
    public BorderPane root;

    public FxRootView(
            final EventHandler shortcutHandler,
            final UIState uiState) {
        super(uiState);
        this.shortcutHandler = shortcutHandler;
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        // TODO: Bind on scene, not root view
        root.setOnKeyPressed(shortcutHandler::handle);
    }

    @Override
    public void switchToInsertMode() {
        root.setCursor(Cursor.CROSSHAIR);
    }

    @Override
    public void switchToSingleSelectionMode() {
        root.setCursor(Cursor.DEFAULT);
    }

    @Override
    public void switchToMultipleSelectionMode() {
        root.setCursor(Cursor.DEFAULT);
    }
}
