package org.example.demo.qt.port.ui.canvas;

import lombok.SneakyThrows;
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

import org.example.demo.qt.port.ui.bridge.*;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.Optional;

import static java.lang.foreign.ValueLayout.*;

/**
 * Qt's realization of {@link ShapeCanvasView}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtShapeCanvasView extends ShapeCanvasView<QtPainter> implements QtMemoryView {
    //<editor-fold desc="ABI method's names">
    private static final String NATIVE_SET_MOUSE_PRESSED_CALLBACK_NAME = "set_mouse_pressed_callback";
    private static final String NATIVE_DRAG_DETECTED_CALLBACK_NAME = "set_drag_detected_callback";
    private static final String NATIVE_SET_MOUSE_DRAGGED_CALLBACK_NAME = "set_mouse_dragged_callback";
    private static final String NATIVE_SET_MOUSE_RELEASED_CALLBACK_NAME = "set_mouse_released_callback";

    private static final String NATIVE_CANVAS_REF_NAME = "ui_canvas_get";
    private static final String NATIVE_CANVAS_CONTROLLER_REF_NAME = "ui_canvas_controller_get";
    private static final String NATIVE_INIT_CANVAS_CONTROLLER_NAME = "init_canvas_controller";
    private static final String NATIVE_GET_CURSOR_POSITION_NAME = "get_cursor_position";
    //</editor-fold>

    private MethodHandle getQtRefHandle;

    private MemorySegment onMousePressedSegment;
    private MemorySegment onDragDetectedSegment;
    private MemorySegment onMouseDraggedSegment;
    private MemorySegment onMouseReleasedSegment;

    private MethodHandle canvasWidthHandle;
    private MethodHandle canvasHeightHandle;

    private MethodHandle canvasCursorPositionHandle;

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
        this.getQtRefHandle =
                findNative(
                        NATIVE_CANVAS_REF_NAME,
                        FunctionDescriptor.of(ValueLayout.ADDRESS));

        this.onMousePressedSegment =
                bindMethodToNative(
                        "handleMousePressCallback",
                        void.class,
                        new Class[]{ double.class, double.class, boolean.class, boolean.class },
                        FunctionDescriptor.ofVoid(
                                JAVA_DOUBLE,
                                JAVA_DOUBLE,
                                JAVA_BOOLEAN,
                                JAVA_BOOLEAN
                        ),
                        NATIVE_CANVAS_CONTROLLER_REF_NAME,
                        NATIVE_SET_MOUSE_PRESSED_CALLBACK_NAME
                );

        this.onDragDetectedSegment =
                createBoundSegment("handleDragDetectedCallback", NATIVE_DRAG_DETECTED_CALLBACK_NAME);

        this.onMousePressedSegment =
                createBoundSegment("handleMouseDraggedCallback", NATIVE_SET_MOUSE_DRAGGED_CALLBACK_NAME);

        this.onMousePressedSegment =
                createBoundSegment("handleMouseReleasedCallback", NATIVE_SET_MOUSE_RELEASED_CALLBACK_NAME);

        this.canvasCursorPositionHandle =
                findNative(
                        NATIVE_GET_CURSOR_POSITION_NAME,
                        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS)
                );

        bindMethodToNative(
                "setCanvasBounds",
                void.class,
                new Class[]{ double.class, double.class },
                FunctionDescriptor.ofVoid(
                        JAVA_DOUBLE,
                        JAVA_DOUBLE
                ),
                NATIVE_CANVAS_REF_NAME,
                NATIVE_INIT_CANVAS_CONTROLLER_NAME
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
                        JAVA_DOUBLE,
                        JAVA_DOUBLE
                ),
                NATIVE_CANVAS_CONTROLLER_REF_NAME,
                nativeName
        );
    }

    public void handleMousePressCallback(
            final double mouseX,
            final double mouseY,
            final boolean isAdditional,
            final boolean isShift) {
        super.handleMousePressed(mouseX, mouseY, isAdditional, isShift);
    }

    public void handleDragDetectedCallback(double mouseX, double mouseY) {
        super.handleDragDetected(mouseX, mouseY);
    }

    public void handleMouseDraggedCallback(double mouseX, double mouseY) {
        super.handleMouseDragged(mouseX, mouseY);
    }

    public void handleMouseReleasedCallback(double mouseX, double mouseY) {
        super.handleMouseReleased(mouseX, mouseY);
    }

    public void setCanvasBounds(double width, double height) {
        this.canvasWidth = width;
        this.canvasHeight = height;
    }

    @Override
    @SneakyThrows
    public Optional<double[]> getLocalCursorPosition() {
        try (final Arena arena = Arena.ofConfined()) {
            final MemorySegment pointSegment = arena.allocate(Point.$LAYOUT());
            canvasCursorPositionHandle.invoke(getQtRefHandle.invoke(), pointSegment);

            final double x = Point.x$get(pointSegment);
            final double y = Point.y$get(pointSegment);

            return x > 0 && y > 0 ? Optional.of(new double[] {x, y}) : Optional.empty();
        }

/*        final Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
        final Point2D localPosition = getCanvas().screenToLocal(cursorPosition.getX(), cursorPosition.getY());
        final double x = localPosition.getX();
        final double y = localPosition.getY();
        return getCanvas().contains(x, y) ? Optional.of(new double[] {x, y}) : Optional.empty();*/
        //return Optional.empty();
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
        return this.canvasWidth; //getCanvas().getWidth();
    }

    @Override
    protected double getHeight() {
        return this.canvasHeight; //getCanvas().getHeight();
    }

    private QtCanvasUI getCanvas() {
        return (QtCanvasUI) canvasUI;
    }
}
