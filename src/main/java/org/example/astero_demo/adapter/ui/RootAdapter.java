package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.ui.event.InsertModeEvent;
import org.example.astero_demo.adapter.ui.event.SelectElementEvent;
import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.controller.ViewController;
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
    public LayersAdapter layersRootController;
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
        this.layersRootController.setParent(this);

        root.setUiState(uiState);
    }

    public void onCreateUpdate(final double newShapeX, final double newShapeY) {
        selectElement(newShapeX, newShapeY);
    }

    public void onModifyUpdate() {
        selectElement(uiState.getSelectedX(), uiState.getSelectedY());
    }

    public void onRemoveUpdate() {
        uiState.setIsInInsertMode(false);
        uiState.removeSelection();
        updateChildren();
    }

    private void updateChildren() {
        toolBarRootController.update();
        canvasRootController.update();
        propertyRootController.update();
        layersRootController.update();
        root.update();
    }

    @Override
    protected void processEvent(final UIEvent event) {
        if (event instanceof final SelectElementEvent e) {
            selectElement(e.getX(), e.getY());
        }
        if (event instanceof final InsertModeEvent e) {
            uiState.setIsInInsertMode(true);
            uiState.setInsertShapeType(e.getInsertShapeType());
            updateChildren();
        }
    }

    private void selectElement(final double shapeX, final double shapeY) {
        uiState.setIsInInsertMode(false);
        updateChildren();

        final ShapeElement selectedShape = canvasRootController.selectElement(shapeX, shapeY);
        final Integer shapeId = selectedShape != null ? selectedShape.getModelRelatedId() : null;

        uiState.setSelectShape(shapeId);
        updateChildren();
    }
}
