package org.example.astero_demo.port.ui.canvas.tool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lombok.Setter;
import org.example.astero_demo.adapter.model.ParamInfo;
import org.example.astero_demo.adapter.model.Shape;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.port.ui.canvas.CanvasView;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;
import org.example.astero_demo.util.ColorUtils;

import static java.lang.Double.parseDouble;
import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.ParamInfo.create;

public class DragShapeTool extends DraggableTool {

    //private final CanvasView canvasView;
    private final CanvasView.CanvasDelegate delegate;
    private final StateHolder stateHolder;
    private final UIState uiState;

    private Color color;

    private double xOffset;
    private double yOffset;

    public DragShapeTool(
            final CanvasView.CanvasDelegate delegate,
            final StateHolder stateHolder,
            final UIState uiState) {
        super(-1, -1, -1, -1, 1);
        this.delegate = delegate;
        this.stateHolder = stateHolder;
        this.uiState = uiState;
        this.color = null;
        this.xOffset = -1;
        this.yOffset = -1;
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(color);
        gc.setGlobalAlpha(OPACITY);
        switch (uiState.getSelectedShapeType()) {
            case RECT:
                gc.fillRect(x - xOffset, y - yOffset, width, height);
                break;
            case OVAL:
                gc.fillOval(x - xOffset, y - yOffset, width, height);
                break;
        }
    }

    @Override
    public void destroyLinks() {

    }

    @Override
    public boolean onDragDetected(final MouseEvent event) {
        final double mouseX = event.getX();
        final double mouseY = event.getY();

        final Shape element =  stateHolder.findShapes(shape -> shape.isInBounds(mouseX, mouseY))
                .findFirst()
                .orElse(null);

        if (element == null || !element.isInBounds(mouseX, mouseY)) {
            // TODO: always false?
            return false;
        }

        this.x = mouseX;
        this.y = mouseY;
        this.width = parseDouble(element.getWidth());
        this.height = parseDouble(element.getHeight());
        this.color = ColorUtils.convert(element.getColor());
        this.xOffset = mouseX - parseDouble(element.getX());
        this.yOffset = mouseY - parseDouble(element.getY());
        this.isActive = true;
        //this.isActive = true;

        return true;
    }

    @Override
    public void onMouseDragged(final double mouseX, final double mouseY) {
        if (!isActive) {
            return;
        }

        this.x = mouseX;
        this.y = mouseY;
        this.isVisible = true;
    }

    @Override
    public void onMouseReleased(final MouseEvent event, final boolean isOnBounds) {
        this.isActive = false;
        if (!isVisible) {
            return;
        }

        final double[] dragPosition = new double[] {x - xOffset, y - yOffset};
        reset();
        if (uiState.hasSelectedId() && isOnBounds) {
            delegate.onDragOver(dragPosition[0], dragPosition[1]);
        }
    }
}
