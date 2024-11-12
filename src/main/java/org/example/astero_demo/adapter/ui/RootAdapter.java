package org.example.astero_demo.adapter.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.astero_demo.port.ui.RootView;

import java.net.URL;
import java.util.ResourceBundle;

public class RootAdapter implements Initializable {
    /*@FXML*/
    public ToolBarAdapter toolBarRootController;
    /*@FXML*/
    public CanvasAdapter canvasController;
    /*@FXML*/
    public RootView root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("root init " + this.toolBarRootController.toString());
    }
}
