package org.example.astero_demo.port.ui;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import lombok.Setter;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class RootView implements Initializable, UpdatableView {
    @Setter
    private UIState uiState;

    //public ToolBarAdapter toolBarRootController;
    public ShapeCanvasView canvasRootController;
    //public PropertyPanelAdapter propertyRootController;
    public LayersPanelView layersRootController;
    private final EventHandler shortcutHandler;
    public BorderPane root;

    public ShapeCanvasView canvasRoot;

/*    private final RootAdapter rootAdapter;*/

    public RootView(/*final RootAdapter rootAdapter, */final EventHandler shortcutHandler) {
        /*this.rootAdapter = rootAdapter;*/
        this.shortcutHandler = shortcutHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //this.toolBarRootController.setParent(this);
        //this.canvasRootController.setParent(this);
        //this.propertyRootController.setParent(this);
        //this.layersRootController.setParent(this);

        //root.setUiState(uiState);
        root.setOnKeyPressed(shortcutHandler::handle);
    }

    @Override
    public void update() {
        root.setCursor(uiState.isInInsertMode() ? Cursor.CROSSHAIR : Cursor.DEFAULT);

        //toolBarRootController.update();
        canvasRootController.update();
        //propertyRootController.update();
        layersRootController.update();
    }
}
