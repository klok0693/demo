package org.example.astero_demo.adapter.ui;

import javafx.fxml.Initializable;
import org.example.astero_demo.adapter.ui.event.InsertModeEvent;
import org.example.astero_demo.adapter.ui.event.SelectElementEvent;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ApplicationEvent;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.port.ui.RootView;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

import java.net.URL;
import java.util.ResourceBundle;

public class RootAdapter extends ParentAdapter {
    /*@FXML*/
    public ToolBarAdapter toolBarRootController;
    /*@FXML*/
    public CanvasAdapter canvasRootController;
    public PropertyAdapter propertyRootController;
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
        this.propertyRootController.setParent(this);

        root.setUiState(uiState);
    }

    @Override
    public void update() {
        rollbackState();
        updateChildren();
    }

    private void rollbackState() {
        uiState.setIsInInsertMode(false);
        uiState.removeSelection();
    }

    private void updateChildren() {
        toolBarRootController.update();
        canvasRootController.update();
        propertyRootController.update();
        root.update();
    }

    @Override
    protected void processEvent(final UIEvent event) {
        if (event instanceof SelectElementEvent) {
            final SelectElementEvent e = (SelectElementEvent) event;
            final ShapeElement selectedShape = canvasRootController.selectElement(e.getX(), e.getY());
            uiState.setSelectShape(selectedShape);
            updateChildren();
        }
        if (event instanceof InsertModeEvent) {
            uiState.setIsInInsertMode(true);
            updateChildren();
        }
    }
}
