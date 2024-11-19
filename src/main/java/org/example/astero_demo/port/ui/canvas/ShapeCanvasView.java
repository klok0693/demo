package org.example.astero_demo.port.ui.canvas;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.port.ui.canvas.element.ShapeLayer;
import org.example.astero_demo.port.ui.canvas.tool.ToolLayer;

public class ShapeCanvasView extends Canvas implements CanvasAdapter.CanvasView {
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
            final ToolLayer toolLayer) {
        this.modelState = modelState;
        this.uiState = uiState;
        this.adapter = adapter;

        setFocusTraversable(true);

        layers.add(backgroundLayer);

        this.shapeLayer = new ShapeLayer(getGraphicsContext2D(), modelState);
        layers.add(shapeLayer);

        this.toolLayer = toolLayer;
        layers.add(toolLayer);

        setOnMousePressed(this::onMousePressed);
        setOnDragDetected(this::onDragDetected);
        setOnMouseDragged(this::onMouseDragged);
        setOnMouseReleased(this::onMouseReleased);

        redraw();
    }

    private void onMousePressed(final MouseEvent event) {
        final double mouseX = event.getX();
        final double mouseY = event.getY();

        requestFocus();

        if (uiState.isInInsertMode()) {
            toolLayer.onMousePressed(mouseX, mouseY);
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
            adapter.primaryMouseBtnPressed(mouseX, mouseY);
            toolLayer.onMousePressed(mouseX, mouseY);
            redraw();
        }
        // Second we check, is there is any tool
        else if (toolLayer.isInBounds(mouseX, mouseY)) {
            toolLayer.onMousePressed(mouseX, mouseY);
            redraw();
        }
        // If there is no tool but a shape under the cursor,
        // we can select it(nothing badly, if it has already been selected)
        else {
            adapter.primaryMouseBtnPressed(mouseX, mouseY);
            toolLayer.onMousePressed(mouseX, mouseY);
            redraw();
        }
        event.consume();
    }

    private void onDragDetected(final MouseEvent event) {
        toolLayer.onDragDetected(event.getX(), event.getY());
        redraw();
    }

    private void onMouseDragged(final MouseEvent event) {
        final double mouseX = event.getX();
        final double mouseY = event.getY();

        final double endX = getLayoutX() + getWidth();
        final double endY = getLayoutY() + getHeight();

        final double toolX = Math.min(Math.max(0, mouseX), endX);
        final double toolY = Math.min(Math.max(0, mouseY), endY);

        toolLayer.onMouseDragged(toolX, toolY);
        redraw();
    }

    private void onMouseReleased(final MouseEvent event) {
        toolLayer.onMouseReleased(event);
        redraw();
    }

    public final void redraw() {
        Platform.runLater(() -> layers.stream().sorted().forEach(layer -> layer.draw(getGraphicsContext2D())));
    }

    @Override
    public void update() {
        shapeLayer.update();
        if (uiState.isInInsertMode() || !uiState.hasSelectedId()) {
            toolLayer.resetAll();
        }
        redraw();
    }

    @Override
    public Shape selectElement(final int id) {
        final Shape element = modelState.getShape(id);
        toolLayer.selectElement(id);
        redraw();
        return element;
    }

    @Override
    public Shape selectElement(final double mouseX, final double mouseY) {
        final Shape element = modelState.findTopShapeAt(mouseX, mouseY).orElse(null);;
        toolLayer.onMousePressed(mouseX, mouseY);
        redraw();
        return element;
    }
}
