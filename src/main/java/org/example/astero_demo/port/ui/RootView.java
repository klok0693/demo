package org.example.astero_demo.port.ui;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.mode.InsertModeSwitchable;
import org.example.astero_demo.adapter.ui.state.mode.SingleSelectionModeSwitchable;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Represents the root view of the application, responsible for managing the main View components.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class RootView implements Initializable, UpdatableView, InsertModeSwitchable, SingleSelectionModeSwitchable {
    private final EventHandler shortcutHandler;
    private final UIState uiState;

    public ToolBarView toolBarRootController;
    public ShapeCanvasView canvasRootController;
    public PropertiesPanelView propertyRootController;
    public LayersPanelView layersRootController;
    public BorderPane root;

    public ShapeCanvasView canvasRoot;

    public RootView(final EventHandler shortcutHandler, final UIState uiState) {
        this.shortcutHandler = shortcutHandler;
        this.uiState = uiState;
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        root.setOnKeyPressed(shortcutHandler::handle);
    }

    @Override
    public void update() {
        root.setCursor(uiState.isInInsertMode() ? Cursor.CROSSHAIR : Cursor.DEFAULT);
    }

    @Override
    public void switchToInsertMode() {
        root.setCursor(Cursor.CROSSHAIR);
    }

    @Override
    public void switchToSingleSelectionMode() {
        root.setCursor(Cursor.DEFAULT);
    }
}
