package org.example.demo.qt.port.ui.canvas;

import org.example.demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.canvas.ShapeCanvasView;
import org.example.demo.qt.port.ui.QtMemoryView;
import org.example.demo.qt.port.ui.canvas.background.QtBackgroundLayer;
import org.example.demo.qt.port.ui.canvas.shape.QtShapeLayer;
import org.example.demo.qt.port.ui.canvas.tool.QtToolLayer;
import org.example.demo.qt.port.ui.element.QtCanvasUI;
import org.example.demo.qt.port.ui.graphics.QtPainter;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.Optional;

/**
 * JavaFX's realization of {@link ShapeCanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtShapeCanvasView extends ShapeCanvasView<QtPainter> implements QtMemoryView {
    private MemorySegment onMousePressedSegment;
    private MemorySegment onDragDetectedSegment;
    private MemorySegment onMouseDraggedSegment;
    private MemorySegment onMouseReleasedSegment;

    private MethodHandle canvasWidthHandle;
    private MethodHandle canvasHeightHandle;

    private double canvasWidth;
    private double canvasHeight;

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

    @Override
    public void initialize() throws Throwable {
        this.onMousePressedSegment =
                createBoundSegment("handleMousePressCallback", "setMousePressedCallback");

        this.onDragDetectedSegment =
                createBoundSegment("handleDragDetectedCallback", "setDragDetectedCallback");

        this.onMousePressedSegment =
                createBoundSegment("handleMouseDraggedCallback", "setMouseDraggedCallback");

        this.onMousePressedSegment =
                createBoundSegment("handleMouseReleasedCallback", "setMouseReleasedCallback");

        bindMethodToNative(
                "setCanvasBounds",
                void.class,
                new Class[]{ double.class, double.class },
                FunctionDescriptor.ofVoid(
                        ValueLayout.JAVA_DOUBLE,
                        ValueLayout.JAVA_DOUBLE
                ),
                "ui_canvas_get",
                "initCanvasController"
        );
    }

    private MemorySegment createBoundSegment(
            final String javaName,
            final String nativeName)
            throws Throwable {

        return bindMethodToNative(
                javaName,
                void.class,
                new Class[]{ double.class, double.class },
                FunctionDescriptor.ofVoid(
                        ValueLayout.JAVA_DOUBLE,
                        ValueLayout.JAVA_DOUBLE
                ),
                "ui_canvas_controller_get",
                nativeName
        );
    }

    /*@Override*/
    public void handleMousePressCallback(double mouseX, double mouseY) {
        System.out.println("handle pressed " + mouseX + " " + mouseY);
        super.handleMousePressed(mouseX, mouseY, false, false);
    }

    public void handleDragDetectedCallback(double mouseX, double mouseY) {
        //System.out.println("handle drag detected");
        super.handleDragDetected(mouseX, mouseY);
    }

    public void handleMouseDraggedCallback(double mouseX, double mouseY) {
        //System.out.println("handle dragged");
        super.handleMouseDragged(mouseX, mouseY);
    }

    public void handleMouseReleasedCallback(double mouseX, double mouseY) {
        System.out.println("handle released " + mouseX + " " + mouseY);
        super.handleMouseReleased(mouseX, mouseY);
    }

    public void setCanvasBounds(double width, double height) {
        System.out.println("Canvas bounded");
        this.canvasWidth = width;
        this.canvasHeight = height;
    }

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
        return this.canvasWidth; //700.0;//getCanvas().getWidth();
    }

    @Override
    protected double getHeight() {
        return this.canvasHeight; //700.0;//getCanvas().getHeight();
    }

    private QtCanvasUI getCanvas() {
        return (QtCanvasUI) canvasUI;
    }
}
