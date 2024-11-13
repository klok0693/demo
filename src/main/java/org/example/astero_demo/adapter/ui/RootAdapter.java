package org.example.astero_demo.adapter.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.port.ui.RootView;

import java.net.URL;
import java.util.ResourceBundle;

public class RootAdapter extends UIAdapter implements Initializable {
    /*@FXML*/
    public ToolBarAdapter toolBarRootController;
    /*@FXML*/
    public CanvasAdapter canvasRootController;
    /*@FXML*/
    public RootView root;

    public RootAdapter(final ViewController controller) {
        super(controller);
        controller.setRootAdapter(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("root init " + this.toolBarRootController.toString());
    }

    @Override
    public void update() {
        toolBarRootController.update();
        canvasRootController.update();
    }
}
