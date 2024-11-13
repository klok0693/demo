package org.example.astero_demo.adapter.ui;

import javafx.fxml.Initializable;
import org.example.astero_demo.adapter.ui.event.SelectElementEvent;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
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

    public RootAdapter(final ViewController controller, final MutableUIState uiState) {
        super(controller, uiState);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("root init " + this.toolBarRootController.toString());

        this.toolBarRootController.setParent(this);
        this.canvasRootController.setParent(this);
    }

    @Override
    public void update() {
        toolBarRootController.update();
        canvasRootController.update();
    }

    @Override
    protected void processEvent(final UIEvent event) {
        if (event instanceof SelectElementEvent) {
            SelectElementEvent e = (SelectElementEvent) event;
            canvasRootController.selectElement(e.getX(), e.getY());
        }
    }
}
