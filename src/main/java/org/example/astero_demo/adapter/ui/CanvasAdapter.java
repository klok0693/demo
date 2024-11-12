package org.example.astero_demo.adapter.ui;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.example.astero_demo.port.ui.canvas.CanvasView;

import java.net.URL;
import java.util.ResourceBundle;

public class CanvasAdapter implements Initializable {
    public CanvasView canvas;
    public AnchorPane canvasRoot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //canvas.redraw();
    }
}
