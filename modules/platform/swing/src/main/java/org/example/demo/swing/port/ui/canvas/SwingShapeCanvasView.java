package org.example.demo.swing.port.ui.canvas;

import lombok.Getter;
import lombok.Setter;
import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.demo.swing.port.ui.canvas.background.SwingBackgroundLayer;
import org.example.demo.swing.port.ui.canvas.shape.SwingShapeLayer;
import org.example.demo.swing.port.ui.canvas.tool.SwingToolLayer;
import org.example.demo.swing.port.ui.element.SwingCanvasUI;
import org.example.demo.swing.port.ui.graphics.SwingPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;

/**
 * Swing realization of {@link ShapeCanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.2
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
        final Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
        final Point localPoint = new Point(cursorPosition);

        SwingUtilities.convertPointFromScreen(localPoint, canvas);

        return canvas.contains(localPoint)
                ? Optional.of(new double[] {localPoint.getX(), localPoint.getY()})
                : Optional.empty();
    }

    @Override
    protected double getLayoutX() {
        return canvas.getX();
    }

    @Override
    protected double getLayoutY() {
        return canvas.getY();
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
