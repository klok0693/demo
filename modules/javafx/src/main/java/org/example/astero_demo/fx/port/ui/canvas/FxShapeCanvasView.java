package org.example.astero_demo.fx.port.ui.canvas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.fx.port.ui.canvas.background.FxBackgroundLayer;
import org.example.astero_demo.fx.port.ui.canvas.shape.FxShapeLayer;
import org.example.astero_demo.fx.port.ui.canvas.tool.FxToolLayer;
import org.example.astero_demo.fx.port.ui.element.FxCanvas;

import java.awt.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * JavaFX's realization of {@link ShapeCanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxShapeCanvasView extends ShapeCanvasView implements Initializable {
    @Getter
    @FXML
    public FxCanvas canvas;

    protected final List<CanvasLayer> layers;

    protected final FxShapeLayer shapeLayer;
    protected final FxToolLayer toolLayer;

    public FxShapeCanvasView(
            final UIState uiState,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final FxBackgroundLayer backgroundLayer,
            final FxShapeLayer shapeLayer,
            final FxToolLayer toolLayer) {
        super(uiState, modelState, adapter);

        layers = new LinkedList<>();
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

        canvas.requestFocus();

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

        final double endX = canvas.getLayoutX() + canvas.getWidth();
        final double endY = canvas.getLayoutY() + canvas.getHeight();

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

    @Override
    protected void redraw() {
        layers.stream().sorted().forEach(layer -> layer.draw(canvas.getGraphicsContext2D()));
    }

    @Override
    public void update() {
        shapeLayer.update();
        toolLayer.update();
        redraw();
    }

    @Override
    public void switchToInsertMode() {
        toolLayer.switchToInsertMode();
        redraw();
    }

    @Override
    public void switchToSingleSelectionMode() {
        toolLayer.switchToSingleSelectionMode();
        redraw();
    }

    @Override
    public void switchToMultipleSelectionMode() {
        toolLayer.switchToMultipleSelectionMode();
        redraw();
    }

    @Override
    public double[] getLocalCursorPosition() {
        final Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
        final Point2D localPosition = canvas.screenToLocal(cursorPosition.getX(), cursorPosition.getY());
        return new double[] {localPosition.getX(), localPosition.getY()};
    }
}
