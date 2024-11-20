package org.example.astero_demo.adapter.ui;

import javafx.geometry.Point2D;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.event.*;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.port.ui.RootView;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class RootAdapter extends ParentAdapter {
    public ToolBarAdapter toolBarRootController;
    public CanvasAdapter canvasRootController;
    public PropertyPanelAdapter propertyRootController;
    public LayersAdapter layersRootController;
    private final RootShortcutHandler shortcutHandler;
    public RootView root;

    public ShapeCanvasView canvasRoot;

    public RootAdapter(final ViewController controller, final MutableUIState uiState, final RootShortcutHandler shortcutHandler) {
        super(controller, uiState);
        this.shortcutHandler = shortcutHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("root init " + this.toolBarRootController.toString());

        this.toolBarRootController.setParent(this);
        this.canvasRootController.setParent(this);
        this.propertyRootController.setParent(this);
        this.layersRootController.setParent(this);

        root.setUiState(uiState);
        root.setOnKeyPressed(shortcutHandler::handle);
    }

    public void onCreateUpdate(final int id) {
        //selectElement(newShapeX, newShapeY);
        selectElement(id);
    }

    public void onModifyUpdate(final int id) {
        selectElement(id);
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
    public void processEvent(final UIEvent event) {
        if (event instanceof final SelectElementByPositionEvent e) {
            selectElement(e.getX(), e.getY());
        }
        else if (event instanceof final SelectElementById e) {
            selectElement(e.getSelectedId());
        }
        else if (event instanceof final InsertModeEvent e) {
            uiState.setIsInInsertMode(true);
            uiState.setInsertShapeType(e.getInsertShapeType());
            updateChildren();
        }
        else if (event instanceof final CopyShapeEvent e) {
            uiState.storeCopyOf(uiState.getSelectedShapeId());
        }
        else if (event instanceof final PasteShapeEvent e) {
            final Point cursorPosition = MouseInfo.getPointerInfo().getLocation(); // Get cursor position on screen
            final Point2D localPosition = canvasRoot.screenToLocal(cursorPosition.getX(), cursorPosition.getY());

            updateChildren();

            controller.process(new CreateNewShapeEvent(
                    parseInt(uiState.getCopyPriority()),
                    localPosition.getX(),
                    localPosition.getY(),
                    parseDouble(uiState.getCopyWidth()),
                    parseDouble(uiState.getCopyHeight()),
                    parseInt(uiState.getCopyColor()),
                    ShapeType.valueOf(uiState.getCopyType()))
            );
        }
    }

    private void selectElement(final int id) {
        uiState.setIsInInsertMode(false);
        updateChildren();

        final Shape selectedShape = canvasRootController.selectElement(id);
        final Integer shapeId = selectedShape != null ? selectedShape.getId() : null;

        uiState.setSelectShape(shapeId);
        updateChildren();
    }

    private void selectElement(final double shapeX, final double shapeY) {
        uiState.setIsInInsertMode(false);
        updateChildren();

        final Shape selectedShape = canvasRootController.selectElement(shapeX, shapeY);
        final Integer shapeId = selectedShape != null ? selectedShape.getId() : null;

        uiState.setSelectShape(shapeId);
        updateChildren();
    }
}
