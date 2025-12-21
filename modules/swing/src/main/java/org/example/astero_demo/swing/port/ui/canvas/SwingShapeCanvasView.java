package org.example.astero_demo.swing.port.ui.canvas;

import lombok.Getter;
import lombok.Setter;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.astero_demo.swing.port.ui.canvas.background.SwingBackgroundLayer;
import org.example.astero_demo.swing.port.ui.canvas.shape.SwingShapeLayer;
import org.example.astero_demo.swing.port.ui.canvas.tool.SwingToolLayer;
import org.example.astero_demo.swing.port.ui.element.SwingCanvasUI;
import org.example.astero_demo.swing.port.ui.graphics.SwingPainter;

import java.awt.event.*;
import java.util.Optional;

/**
 * Swing realization of {@link ShapeCanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingShapeCanvasView extends ShapeCanvasView<SwingPainter> {
    private final SwingCanvasUI canvas;

    public SwingShapeCanvasView(
            final UIState uiState,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final SwingBackgroundLayer backgroundLayer,
            final SwingShapeLayer shapeLayer,
            final SwingToolLayer toolLayer,
            final SwingCanvasUI canvasUI) {
        super(uiState, modelState, adapter, backgroundLayer, shapeLayer, toolLayer, canvasUI);

        this.canvas = canvasUI;
        canvas.repaint();

        @Getter @Setter
        class DragSwitch {
            private boolean isDragStarted;
        }
        final DragSwitch dragSwitch = new DragSwitch();

        canvas.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(final MouseEvent e) {
                if (!dragSwitch.isDragStarted()) {
                    handleDragDetected(e);
                    dragSwitch.setDragStarted(true);
                }
                handleMouseDragged(e);
                canvas.repaint();
            }
        });
        canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent e) {
                handleMousePressed(e);
                dragSwitch.setDragStarted(false);
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                handleMouseReleased(e);
                dragSwitch.setDragStarted(false);
            }
        });
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
    public Optional<double[]> getLocalCursorPosition() {
        return Optional.empty();
/*        final Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
        final Point2D localPosition = canvas.screenToLocal(cursorPosition.getX(), cursorPosition.getY());
        final double x = localPosition.getX();
        final double y = localPosition.getY();
        return canvas.contains(x, y) ? Optional.of(new double[] {x, y}) : Optional.empty();*/
    }

    @Override
    protected double getLayoutX() {
        return canvas.getX();//.getLayoutX();
    }

    @Override
    protected double getLayoutY() {
        return canvas.getY();//.getLayoutY();
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
