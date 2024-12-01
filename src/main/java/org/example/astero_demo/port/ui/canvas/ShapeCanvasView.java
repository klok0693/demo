package org.example.astero_demo.port.ui.canvas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.adapter.ui.state.mode.InsertModeSwitchable;
import org.example.astero_demo.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.model.state.ModelState;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.port.ui.canvas.shape.ShapeLayer;
import org.example.astero_demo.port.ui.canvas.tool.ToolLayer;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Represents a canvas view that displays shapes and provide tools<p>
 * to perform operations with its content. All elements are drawn on<p>
 * a separate layers: first background, next shapes and tools on top of it<p>
 * Also delegate mouse events to its child elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ShapeCanvasView extends Canvas implements CanvasView, Initializable {
    private final ObservableList<CanvasLayer> layers = FXCollections.observableArrayList();
    private final ShapeLayer shapeLayer;
    private final ToolLayer toolLayer;
    private final UIState uiState;
    private final ModelState modelState;
    private final CanvasAdapter adapter;

    public ShapeCanvasView(
            final UIState uiState,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final BackgroundLayer backgroundLayer,
            final ShapeLayer shapeLayer,
            final ToolLayer toolLayer) {
        this.modelState = modelState;
        this.uiState = uiState;
        this.adapter = adapter;

        layers.add(backgroundLayer);

        this.shapeLayer = shapeLayer;
        layers.add(shapeLayer);

        this.toolLayer = toolLayer;
        layers.add(toolLayer);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        redraw();
    }

    public void handleMousePressed(final MouseEvent event) {
        final double mouseX = event.getX();
        final double mouseY = event.getY();
        final boolean isAdditional = event.isControlDown();

        requestFocus();

        if (uiState.isInInsertMode()) {
            toolLayer.onMousePressed(event);
            event.consume();
            return;
        }

        /* For now, there are 4 possible logic ways, when we are not in insert mode:
             1) There are no any shape or tool under the cursor
             2) There are already selected shape under the cursor
             3) There are some active tool under cursor(as the example, Ovals has round visible bounds, but
                there's tool bounds are rectangle. So, situation, when the cursor would be not in visible,
                but in tool border, is possible)
             4) There are not selected shape under the cursor

             Order of the checks is important*/

        // First we check, is there are no shape or tool under the cursor
        if (!toolLayer.isInBounds(mouseX, mouseY) && !modelState.findTopVisibleShape(mouseX, mouseY).isPresent()) {
            adapter.primaryMouseBtnPressed(mouseX, mouseY, isAdditional);
            toolLayer.onMousePressed(event);
            redraw();
        }
        // Second we check, is there is any tool
        else if (toolLayer.isInBounds(mouseX, mouseY)) {
            toolLayer.onMousePressed(event);
            redraw();
        }
        // If there is no tool but a shape under the cursor,
        // we can select it(nothing badly, if it has already been selected)
        else {
            adapter.primaryMouseBtnPressed(mouseX, mouseY, isAdditional);
            update();
            redraw();
        }
        event.consume();
    }

    public void handleDragDetected(final MouseEvent event) {
        toolLayer.onDragDetected(event);
        redraw();
        event.consume();
    }

    public void handleMouseDragged(final MouseEvent event) {
        final double mouseX = event.getX();
        final double mouseY = event.getY();

        final double endX = getLayoutX() + getWidth();
        final double endY = getLayoutY() + getHeight();

        final double toolX = Math.min(Math.max(0, mouseX), endX);
        final double toolY = Math.min(Math.max(0, mouseY), endY);

        toolLayer.onMouseDragged(toolX, toolY);
        redraw();
        event.consume();
    }

    public void handleMouseReleased(final MouseEvent event) {
        toolLayer.onMouseReleased(event);
        redraw();
        event.consume();
    }

    private void redraw() {
        layers.stream().sorted().forEach(layer -> layer.draw(getGraphicsContext2D()));
    }

    @Override
    public void update() {
        shapeLayer.update();
        toolLayer.update();
        redraw();
    }

    @Override
    public void switchToInsertMode() {
        shapeLayer.switchToInsertMode();
        toolLayer.switchToInsertMode();
        redraw();
    }

    @Override
    public void switchToSingleSelectionMode() {
        shapeLayer.switchToSingleSelectionMode();
        toolLayer.switchToSingleSelectionMode();
        redraw();
    }

    @Override
    public double[] getLocalCursorPosition() {
        final Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
        final Point2D localPosition = screenToLocal(cursorPosition.getX(), cursorPosition.getY());
        return new double[] {localPosition.getX(), localPosition.getY()};
    }
}
