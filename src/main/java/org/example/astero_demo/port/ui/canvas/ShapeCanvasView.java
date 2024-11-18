package org.example.astero_demo.port.ui.canvas;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.port.ui.canvas.element.ShapeLayer;
import org.example.astero_demo.port.ui.canvas.tool.ToolLayer;

import java.util.Optional;

import static java.lang.String.valueOf;

public class ShapeCanvasView extends Canvas implements CanvasAdapter.CanvasView {

    private final ObservableList<CanvasLayer> layers = FXCollections.observableArrayList();
    private final ShapeLayer shapeLayer;
    private final ToolLayer toolLayer;
    private final UIState uiState;
    private final ModelState modelState;

    public ShapeCanvasView(
            final UIState uiState,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final BackgroundLayer backgroundLayer,
            final ToolLayer toolLayer) {
        this.modelState = modelState;
        this.uiState = uiState;
        setFocusTraversable(true);

        layers.add(backgroundLayer);

        this.shapeLayer = new ShapeLayer(getGraphicsContext2D(), modelState);
        layers.add(shapeLayer);

        this.toolLayer = toolLayer;
        layers.add(toolLayer);

        setOnMousePressed(e -> {
            final double mouseX = e.getX();
            final double mouseY = e.getY();

            if (uiState.isInInsertMode()) {
                toolLayer.onMousePressed(mouseX, mouseY);
                e.consume();
                return;
            }

            if (!toolLayer.isInBounds(mouseX, mouseY) && !modelState.findTopVisibleShape(mouseX, mouseY).isPresent()) {
                adapter.primaryMouseBtnPressed(mouseX, mouseY);
                toolLayer.onMousePressed(mouseX, mouseY);
                redraw();
            }
            else if (toolLayer.isInBounds(mouseX, mouseY)) {
                //adapter.primaryMouseBtnPressed(mouseX, mouseY);
                toolLayer.onMousePressed(mouseX, mouseY);
                redraw();
            }
            else {
                adapter.primaryMouseBtnPressed(mouseX, mouseY);
                toolLayer.onMousePressed(mouseX, mouseY);
                redraw();
            }

/*            if (!hasElementOn(mouseX, mouseY) && !toolLayer.isInBounds(mouseX, mouseY)) {
                adapter.primaryMouseBtnPressed(mouseX, mouseY);
                redraw();
            }
            else if (toolLayer.isInBounds(mouseX, mouseY)) {
                toolLayer.onMousePressed(mouseX, mouseY);//.onDragDetected(e);
                redraw();
            }
            else {
                adapter.primaryMouseBtnPressed(mouseX, mouseY);
                toolLayer.onMousePressed(mouseX, mouseY);//.onDragDetected(e);
                redraw();
            }*/
            e.consume();
        });

        setOnDragDetected(event -> {
            toolLayer.onDragDetected(event.getX(), event.getY());
            redraw();
        });

        setOnMouseDragged(event -> {
            final double mouseX = event.getX();
            final double mouseY = event.getY();

            final double endX = getLayoutX() + getWidth();
            final double endY = getLayoutY() + getHeight();

            final double toolX = Math.min(Math.max(0, mouseX), endX);
            final double toolY = Math.min(Math.max(0, mouseY), endY);

            toolLayer.onMouseDragged(toolX, toolY);
            redraw();
        });

        setOnMouseReleased(event -> {
            toolLayer.onMouseReleased(event);
            redraw();
        });

        redraw();
    }

    public final void redraw() {
        Platform.runLater(() -> {
            layers.stream().sorted().forEach(layer -> layer.draw(getGraphicsContext2D()));
        });
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
    public Shape selectElement(int id) {
        final Shape element = modelState.getShape(id);
        final double x = element == null ? -1 : element.getCenterX();
        final double y = element == null ? -1 : element.getCenterY();
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

    private boolean hasElementOn(final double mouseX, final double mouseY) {
        return modelState.findTopShapeAt(mouseX, mouseY).isPresent();
    }
}
