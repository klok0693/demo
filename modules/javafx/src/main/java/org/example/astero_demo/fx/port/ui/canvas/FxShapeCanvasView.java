package org.example.astero_demo.fx.port.ui.canvas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.fx.port.ui.canvas.background.FxBackgroundLayer;
import org.example.astero_demo.fx.port.ui.canvas.shape.FxShapeLayer;
import org.example.astero_demo.fx.port.ui.canvas.tool.FxToolLayer;
import org.example.astero_demo.fx.port.ui.element.FxCanvas;
import org.example.astero_demo.fx.port.ui.graphics.FxPainter;

import java.awt.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * JavaFX's realization of {@link ShapeCanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxShapeCanvasView extends ShapeCanvasView<FxPainter> implements Initializable {
    @FXML
    public FxCanvas canvas;

    public FxShapeCanvasView(
            final UIState uiState,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final FxBackgroundLayer backgroundLayer,
            final FxShapeLayer shapeLayer,
            final FxToolLayer toolLayer) {
        super(uiState, modelState, adapter, backgroundLayer, shapeLayer, toolLayer);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        redraw();
    }

    public void handleMousePressed(final MouseEvent event) {
        canvas.requestFocus();
        handleMousePressed(
                event.getX(),
                event.getY(),
                event.isControlDown(),
                event.isShiftDown());

        event.consume();
    }

    public void handleDragDetected(final MouseEvent event) {
        handleDragDetected(event.getX(), event.getY());
        event.consume();
    }

    public void handleMouseDragged(final MouseEvent event) {
        handleMouseDragged(event.getX(), event.getY());
        event.consume();
    }

    public void handleMouseReleased(final MouseEvent event) {
        handleMouseReleased(event.getX(), event.getY());
        event.consume();
    }

    @Override
    public FxPainter getGraphicsPainter() {
        return new FxPainter(canvas.getGraphicsContext2D());
    }

    @Override
    public Optional<double[]> getLocalCursorPosition() {
        final Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
        final Point2D localPosition = canvas.screenToLocal(cursorPosition.getX(), cursorPosition.getY());
        final double x = localPosition.getX();
        final double y = localPosition.getY();
        return canvas.contains(x, y) ? Optional.of(new double[] {x, y}) : Optional.empty();
    }

    @Override
    protected double getLayoutX() {
        return canvas.getLayoutX();
    }

    @Override
    protected double getLayoutY() {
        return canvas.getLayoutY();
    }

    @Override
    protected double getWidth() {
        return canvas.getWidth();
    }

    @Override
    protected double getHeight() {
        return canvas.getHeight();
    }
}
