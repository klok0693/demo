package org.example.astero_demo.core.port.ui.canvas;

import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.canvas.background.BackgroundLayer;
import org.example.astero_demo.core.port.ui.canvas.shape.ShapeLayer;
import org.example.astero_demo.core.port.ui.canvas.tool.ToolLayer;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a canvas view that displays shapes and provide tools<p>
 * to perform operations with its content. All elements are drawn on<p>
 * a separate layers: first background, next shapes and tools on top of it<p>
 * Also delegate mouse events to its child elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ShapeCanvasView<E> implements CanvasView, GraphicsContext<E> {
    protected final UIState uiState;
    protected final ModelState modelState;
    protected final CanvasAdapter adapter;

    protected final List<CanvasLayer<E, ?>> layers;

    protected final ShapeLayer<E> shapeLayer;
    protected final ToolLayer<E> toolLayer;

    public ShapeCanvasView(
            final UIState uiState,
            final ModelState modelState,
            final CanvasAdapter adapter,
            final BackgroundLayer<E> backgroundLayer,
            final ShapeLayer<E> shapeLayer,
            final ToolLayer<E> toolLayer) {
        this.modelState = modelState;
        this.uiState = uiState;
        this.adapter = adapter;

        layers = new LinkedList<>();
        layers.add(backgroundLayer);

        this.shapeLayer = shapeLayer;
        layers.add(shapeLayer);

        this.toolLayer = toolLayer;
        layers.add(toolLayer);
    }

    protected void redraw() {
        layers.stream().sorted().forEach(layer -> layer.draw(getGraphicsContext()));
    }

    protected void redraw(final E graphics) {
        layers.stream().sorted().forEach(layer -> layer.draw(graphics));
    }

    @Override
    public void update() {
        shapeLayer.update();
        toolLayer.update();
        redraw();
    }

    @Override
    public void switchToInsertMode() {
        toolLayer.switchToInsertMode();
        redraw();
    }

    @Override
    public void switchToSingleSelectionMode() {
        toolLayer.switchToSingleSelectionMode();
        redraw();
    }

    @Override
    public void switchToMultipleSelectionMode() {
        toolLayer.switchToMultipleSelectionMode();
        redraw();
    }

    protected void handleMousePressed(
            final double mouseX,
            final double mouseY,
            final boolean isAdditional,
            final boolean isShiftDown) {

        if (uiState.isInInsertMode()) {
            toolLayer.onMousePressed(mouseX, mouseY, isShiftDown);
            return;
        }

        /* For now, there are 4 possible logic ways, when we are not in insert mode:
             1) There are no any shape or tool under the cursor
             2) There are already selected shape under the cursor
             3) There are some active tool under cursor(as the example, Ovals has round visible bounds, but
                there's tool bounds are rectangle. So, situation, when the cursor would be not in visible,
                but in tool border, is possible)
             4) There are not selected shape under the cursor

             Order of the checks is important*/

        // First we check, is there are no shape or tool under the cursor
        if (!toolLayer.isInBounds(mouseX, mouseY) && !modelState.findTopVisibleShape(mouseX, mouseY).isPresent()) {
            adapter.primaryMouseBtnPressed(mouseX, mouseY, isAdditional);
            toolLayer.onMousePressed(mouseX, mouseY, isShiftDown);
            redraw();
        }
        // Second we check, is there is any tool
        else if (toolLayer.isInBounds(mouseX, mouseY)) {
            toolLayer.onMousePressed(mouseX, mouseY, isShiftDown);
            redraw();
        }
        // If there is no tool but a shape under the cursor,
        // we can select it(nothing badly, if it has already been selected)
        else {
            adapter.primaryMouseBtnPressed(mouseX, mouseY, isAdditional);
            update();
            redraw();
        }
    }

    protected void handleDragDetected(final double mouseX, final double mouseY) {
        toolLayer.onDragDetected(mouseX, mouseY);
        redraw();
    }

    protected void handleMouseDragged(final double mouseX, final double mouseY) {
        final double endX = getLayoutX() + getWidth();
        final double endY = getLayoutY() + getHeight();

        final double toolX = Math.min(Math.max(0, mouseX), endX);
        final double toolY = Math.min(Math.max(0, mouseY), endY);

        toolLayer.onMouseDragged(toolX, toolY);
        redraw();
    }

    protected void handleMouseReleased(final double mouseX, final double mouseY) {
        toolLayer.onMouseReleased(mouseX, mouseY);
        redraw();
    }

    protected abstract double getLayoutX();

    protected abstract double getLayoutY();

    protected abstract double getWidth();

    protected abstract double getHeight();
}
