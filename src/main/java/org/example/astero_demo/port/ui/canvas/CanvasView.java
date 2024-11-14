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
import org.example.astero_demo.port.ui.canvas.tool.DragShapeTool;
import org.example.astero_demo.port.ui.canvas.tool.ShapeSelectionTool;
import org.example.astero_demo.port.ui.canvas.tool.ToolLayer;
import org.example.astero_demo.util.ColorUtils;

import java.util.Optional;

public class CanvasView extends Canvas {

    private final ChangeListener<Boolean> redrawListener = new WeakChangeListener<>((observableValue, aBoolean, t1) -> {
        if (aBoolean) {
            redraw();
        }
    });

    private ObservableList<CanvasLayer> layers = FXCollections.observableArrayList();
    private final ShapeLayer shapeLayer;
    private final ToolLayer toolLayer;
    @Setter
    private UIState uiState;

    @Setter
    private CanvasDelegate delegate;

    public CanvasView() {
        final CanvasLayer backgroundLayer = new BackgroundLayer(getGraphicsContext2D());
        layers.add(backgroundLayer);

        shapeLayer = new ShapeLayer(getGraphicsContext2D());
        layers.add(shapeLayer);

        toolLayer = new ToolLayer(getGraphicsContext2D());
        layers.add(toolLayer);

        visibleProperty().addListener(redrawListener);
        focusedProperty().addListener(redrawListener);

        setOnMousePressed(this::onMousePressed);

        setOnDragDetected(mouseEvent -> {
            final double mouseX = mouseEvent.getX();
            final double mouseY = mouseEvent.getY();
            if (uiState.hasSelectedId() && isSelectedElementAt(mouseX, mouseY)) {
                final double xOffset = mouseX - uiState.getSelectedX();
                final double yOffset = mouseY - uiState.getSelectedY();
                toolLayer.createDragTool(
                        mouseX,
                        mouseY,
                        uiState.getSelectedWidth(),
                        uiState.getSelectedHeight(),
                        ColorUtils.convert(uiState.getSelectedColor()),
                        xOffset,
                        yOffset
                );
                redraw();
            }
            else {
                delegate.primaryMouseBtnPressed(-1, -1);
            }
        });

        setOnMouseDragged(mouseEvent -> toolLayer.getDragTool()
                .ifPresent(tool -> processDrag(mouseEvent, tool)));

        setOnMouseReleased(mouseEvent -> {
            final Optional<double[]> dragPosition = toolLayer.getDragTool().map(DragShapeTool::getCurrentPosition);
            toolLayer.destroyDragTool();
            redraw();

            if (uiState.hasSelectedId() && getLayoutBounds().contains(mouseEvent.getX(), mouseEvent.getY())) {
                dragPosition.ifPresent(doubles -> {
                    delegate.onDragOver(doubles[0], doubles[1]);
                });
            }
        });

        redraw();
    }

    public final void redraw() {
        Platform.runLater(() -> {
            layers.stream().sorted().forEach(layer -> layer.draw(getGraphicsContext2D()));
            //layers.forEach(layer -> layer.draw(getGraphicsContext2D()));
        });
    }

    public void update(final StateHolder holder, final boolean hideTools) {
        shapeLayer.update(holder);
        if (hideTools) {
            toolLayer.removeAll();
        }
        redraw();
    }

/*    public void unselectAll() {
        selectElement(-1, -1);
    }*/

    public ShapeElement selectElement(final double x, final double y) {
        toolLayer.removeAll();

        final ShapeElement element = shapeLayer.elementAt(x, y);
        if (element != null) {
            final ShapeSelectionTool selectionTool = new ShapeSelectionTool(element.x, element.y, element.width, element.height);
            toolLayer.add(selectionTool);
        }
        redraw();
        return element;
    }

/*    public int getSelectedElementId() {
        return toolLayer.getChildren()
                .findAny()
                .map(tool -> shapeLayer.elementAt(tool.x, tool.y))
                .map(ShapeElement::getModelRelatedId)
                .orElse(-1);
    }*/

/*    public boolean hasAnyElement(final double x, final double y) {
        return shapeLayer.elementAt(x, y) != null;
    }*/

    public boolean isSelectedElementAt(final double x, final double y) {
        final ShapeElement element = shapeLayer.elementAt(x, y);
        if (element != null) {
            return element.getModelRelatedId() == uiState.getSelectedShapeId() && element.isInBounds(x, y);
        }
        return false;
    }

    private void onMousePressed(final MouseEvent e) {
        if (delegate == null) {
            return;
        }

        if (e.getButton() == MouseButton.PRIMARY) {
            delegate.primaryMouseBtnPressed(e.getX(), e.getY());
        }
    }

/*    private CanvasLayer getTopLayer() {
        return layers.stream()
                .max(Comparator.comparingInt(CanvasLayer::getPriority))
                .get();
    }*/

    private void processDrag(final MouseEvent mouseEvent, final DragShapeTool tool) {
        final double mouseX = mouseEvent.getX();
        final double mouseY = mouseEvent.getY();

        final double startX = CanvasView.this.getLayoutX();
        final double startY = CanvasView.this.getLayoutY();

        final double endX = startX + getWidth();
        final double endY = startY + getHeight();

        final double dragX = Math.min(Math.max(0, mouseX), endX);
        final double dragY = Math.min(Math.max(0, mouseY), endY);

        tool.update(dragX, dragY);
        redraw();
    }

/*    private boolean isOnCanvas(final double x, final double y) {
        final double startX = CanvasView.this.getLayoutX();
        final double startY = CanvasView.this.getLayoutY();

        final double endX = startX + getWidth();
        final double endY = startY + getHeight();

        return x >= startX && x
    }*/

    public interface CanvasDelegate {

        void primaryMouseBtnPressed(double x, double y);

        void onDragOver(final double x, final double y);
    }
}
