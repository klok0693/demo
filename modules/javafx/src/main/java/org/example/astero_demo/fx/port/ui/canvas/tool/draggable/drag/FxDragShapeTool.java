package org.example.astero_demo.fx.port.ui.canvas.tool.draggable.drag;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.port.ui.canvas.tool.draggable.drag.DragShapeTool;
import org.example.astero_demo.fx.port.ui.canvas.FxCanvasElement;
import org.example.astero_demo.fx.util.ColorUtils;

/**
 * JavaFX's realization of {@link DragShapeTool}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class FxDragShapeTool extends DragShapeTool<GraphicsContext> implements FxCanvasElement {
    protected Color fillColor;

    public FxDragShapeTool(
            final CanvasAdapter adapter,
            final ModelState modelState,
            final UIState uiState) {
        super(adapter, modelState, uiState);
    }

    @Override
    protected void drawElement(final GraphicsContext gc) {
        gc.setFill(fillColor.darker());
        gc.setGlobalAlpha(OPACITY);

        switch (uiState.getSelectedShapeType()) {
            case RECT -> gc.fillRect(x - offsetX, y - offsetY, width, height);
            case ELLIPSE -> gc.fillOval(x - offsetX, y - offsetY, width, height);
        }
    }

    @Override
    protected void onDragUpdate(final Shape shape) {
        this.fillColor = ColorUtils.convert(shape.getColor());
    }

    @Override
    public void save(final GraphicsContext gc) {
        FxCanvasElement.super.save(gc);
    }

    @Override
    public void restore(final GraphicsContext gc) {
        FxCanvasElement.super.restore(gc);
    }
}
