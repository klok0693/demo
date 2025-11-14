package org.example.astero_demo.core.port.ui.canvas.shape;

import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.core.state.ModelState;
import org.example.astero_demo.core.adapter.ui.UpdatableView;
import org.example.astero_demo.core.port.ui.canvas.CanvasElement;
import org.example.astero_demo.core.port.ui.canvas.CanvasLayer;

import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Layer to for a shape elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ShapeLayer<E> extends CanvasLayer<E, CanvasLayer<E, CanvasElement<E>>> implements UpdatableView {
    protected final ModelState modelState;

    protected ShapeLayer(final ModelState modelState) {
        super(1);
        this.modelState = modelState;
    }

    protected ShapeLayer(final int layer, final ModelState modelState) {
        super(layer);
        this.modelState = modelState;
    }

    @Override
    public void update() {
        removeAll();
        modelState.getShapes()
                .map(this::createElement)
                .collect(Collectors.groupingBy(
                        ShapeElement::getLayer,
                        Collectors.mapping(e -> (CanvasElement<E>) e, Collectors.toList())))
                .forEach((layer, elements) -> {
                    final CanvasLayer<E, CanvasElement<E>> canvasLayer = createLayer(layer);
                    canvasLayer.addAll(elements);
                    children.add(canvasLayer);
                });
    }

    private ShapeElement<E> createElement(final Shape shape) {
        final int layer = parseInt(shape.getPriority());
        final int id = shape.getId();
        final double x = parseDouble(shape.getX());
        final double y = parseDouble(shape.getY());
        final double width = parseDouble(shape.getWidth());
        final double height = parseDouble(shape.getHeight());
        final String fillColor = shape.getColor();

        return switch (shape.getType()) {
            case ELLIPSE -> createEllipse(layer, id, x, y, width, height, fillColor);
            case RECT -> createRectangle(layer, id, x, y, width, height, fillColor);
        };
    }

    protected abstract EllipseElement<E> createEllipse(
            int layer,
            int modelRelatedId,
            double x,
            double y,
            double width,
            double height,
            String fillColor
    );

    protected abstract RectangleElement<E> createRectangle(
            int layer,
            int modelRelatedId,
            double x,
            double y,
            double width,
            double height,
            String fillColor
    );

    protected abstract <T extends CanvasElement<E>> CanvasLayer<E, T> createLayer(int layer);
}
