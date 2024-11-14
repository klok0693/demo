package org.example.astero_demo.port.ui.canvas;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import lombok.Setter;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;
import org.example.astero_demo.port.ui.canvas.element.ShapeLayer;
import org.example.astero_demo.port.ui.canvas.tool.ToolLayer;

public class CanvasView extends Canvas {

    private final ChangeListener<Boolean> redrawListener = new WeakChangeListener<>((observableValue, aBoolean, t1) -> {
        if (aBoolean) {
            redraw();
        }
    });

    private ObservableList<CanvasLayer> layers = FXCollections.observableArrayList();
    private final ShapeLayer shapeLayer;
    private final ToolLayer toolLayer;

    private UIState uiState;

    @Setter
    private CanvasDelegate delegate;

    public CanvasView() {
        final CanvasLayer backgroundLayer = new BackgroundLayer(getGraphicsContext2D());
        layers.add(backgroundLayer);

        shapeLayer = new ShapeLayer(getGraphicsContext2D());
        layers.add(shapeLayer);

        toolLayer = new ToolLayer(getGraphicsContext2D(), this);
        layers.add(toolLayer);

        visibleProperty().addListener(redrawListener);
        focusedProperty().addListener(redrawListener);

        setOnMousePressed(this::onMousePressed);

        setOnDragDetected(mouseEvent -> {
            final double mouseX = mouseEvent.getX();
            final double mouseY = mouseEvent.getY();
            if (uiState.hasSelectedId() && isSelectedElementAt(mouseX, mouseY)) {
                toolLayer.onDragDetected(mouseEvent);
                redraw();
            }
            else {
                delegate.primaryMouseBtnPressed(-1, -1);
            }
        });

        setOnMouseDragged(mouseEvent -> {
            toolLayer.onMouseDragged(mouseEvent);
            redraw();
        });

        setOnMouseReleased(mouseEvent -> {
            toolLayer.onMouseReleased(mouseEvent);
            redraw();
        });

        redraw();
    }

    public final void redraw() {
        Platform.runLater(() -> {
            layers.stream().sorted().forEach(layer -> layer.draw(getGraphicsContext2D()));
        });
    }

    public void update(final StateHolder holder, final boolean hideTools) {
        shapeLayer.update(holder);
        if (hideTools) {
            toolLayer.resetAll();
        }
        redraw();
    }

/*    public void unselectAll() {
        selectElement(-1, -1);
    }*/

    public ShapeElement selectElement(final double x, final double y) {
        final ShapeElement element = elementAt(x, y);
        toolLayer.onMousePressed(x, y);
        redraw();
        return element;
    }

    public ShapeElement elementAt(final double x, final double y) {
        return shapeLayer.elementAt(x, y);
    }

    public boolean isSelectedElementAt(final double x, final double y) {
        final ShapeElement element = elementAt(x, y);
        if (element != null) {
            return element.getModelRelatedId() == uiState.getSelectedShapeId() && element.isInBounds(x, y);
        }
        return false;
    }

    public void onDragOver(final double x, final double y) {
        delegate.onDragOver(x, y);
    }

    public void setUiState(final UIState uiState) {
        this.uiState = uiState;
        toolLayer.setUIState(uiState);
    }

    private void onMousePressed(final MouseEvent e) {
        if (delegate == null) {
            return;
        }

        if (e.getButton() == MouseButton.PRIMARY) {
            delegate.primaryMouseBtnPressed(e.getX(), e.getY());
        }
    }

    public interface CanvasDelegate {

        void primaryMouseBtnPressed(double x, double y);

        void onDragOver(final double x, final double y);
    }
}
