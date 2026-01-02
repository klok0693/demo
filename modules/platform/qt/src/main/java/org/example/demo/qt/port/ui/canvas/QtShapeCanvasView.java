package org.example.demo.qt.port.ui.canvas;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.demo.qt.port.ui.canvas.background.QtBackgroundLayer;
import org.example.demo.qt.port.ui.canvas.shape.QtShapeLayer;
import org.example.demo.qt.port.ui.canvas.tool.QtToolLayer;
import org.example.demo.qt.port.ui.element.QtCanvasUI;
import org.example.demo.qt.port.ui.graphics.QtPainter;

import java.util.Optional;

/**
 * JavaFX's realization of {@link ShapeCanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtShapeCanvasView extends ShapeCanvasView<QtPainter> {

    public QtShapeCanvasView(
            final UIState uiState,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final QtBackgroundLayer backgroundLayer,
            final QtShapeLayer shapeLayer,
            final QtToolLayer toolLayer,
            final QtCanvasUI canvasUI) {
        super(uiState, modelState, adapter, backgroundLayer, shapeLayer, toolLayer, canvasUI);
    }

/*    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        getCanvas().redraw();
    }*/

/*    public void handleMousePressed(final MouseEvent event) {
        getCanvas().requestFocus();
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
    }*/

    @Override
    public Optional<double[]> getLocalCursorPosition() {
/*        final Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
        final Point2D localPosition = getCanvas().screenToLocal(cursorPosition.getX(), cursorPosition.getY());
        final double x = localPosition.getX();
        final double y = localPosition.getY();
        return getCanvas().contains(x, y) ? Optional.of(new double[] {x, y}) : Optional.empty();*/
        return Optional.empty();
    }

    @Override
    protected double getLayoutX() {
        return 0.0;//getCanvas().getLayoutX();
    }

    @Override
    protected double getLayoutY() {
        return 0.0;//getCanvas().getLayoutY();
    }

    @Override
    protected double getWidth() {
        return 0.0;//getCanvas().getWidth();
    }

    @Override
    protected double getHeight() {
        return 0.0;//getCanvas().getHeight();
    }

    private QtCanvasUI getCanvas() {
        return (QtCanvasUI) canvasUI;
    }
}
