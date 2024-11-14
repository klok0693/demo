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
import org.example.astero_demo.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;
import org.example.astero_demo.port.ui.canvas.element.ShapeLayer;
import org.example.astero_demo.port.ui.canvas.tool.ShapeSelectionTool;
import org.example.astero_demo.port.ui.canvas.tool.ToolLayer;

import java.util.Comparator;

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

        setOnMouseClicked(this::onMouseClick);

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

    public void unselectAll() {
        selectElement(-1, -1);
    }

    public ShapeElement selectElement(final double x, final double y) {
        toolLayer.removeAll();

        final ShapeElement element = shapeLayer.elementAt(x, y);
        if (element != null) {
            final ShapeSelectionTool selectionTool = new ShapeSelectionTool(element.x, element.y, 100, 100);
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

    public boolean hasAnyElement(final double x, final double y) {
        return shapeLayer.elementAt(x, y) != null;
    }

    private void onMouseClick(final MouseEvent e) {
        if (delegate == null) {
            return;
        }

        if (e.getButton() == MouseButton.PRIMARY) {
            delegate.primaryMouseBtnClicked(getTopLayer().getPriority(), e.getX(), e.getY());
/*            final var rectangleUI = new RectangleUI(e.getX(), e.getY(), 100, 100, Color.GREEN);
            getTopLayer().add(rectangleUI);
            redraw();*/
        }
    }

    private CanvasLayer getTopLayer() {
        return layers.stream()
                .max(Comparator.comparingInt(CanvasLayer::getPriority))
                .get();
    }

    public interface CanvasDelegate {

        void primaryMouseBtnClicked(int priority, double x, double y);
    }
}
