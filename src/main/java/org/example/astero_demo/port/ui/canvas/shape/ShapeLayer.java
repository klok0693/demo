package org.example.astero_demo.port.ui.canvas.shape;

import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.util.ColorUtils;

import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ShapeLayer extends CanvasLayer<CanvasLayer<ShapeElement>> implements UpdatableView {
    private final ModelState modelState;

    public ShapeLayer(final ModelState modelState) {
        super(1);
        this.modelState = modelState;
    }

    @Override
    public void update() {
        removeAll();
        modelState.getShapes()
                .map(ShapeLayer::createElement)
                .collect(Collectors.groupingBy(ShapeElement::getLayer, Collectors.toList()))
                .forEach((layer, elements) -> {
                    final CanvasLayer<ShapeElement> canvasLayer = new CanvasLayer<>(layer);
                    canvasLayer.addAll(elements);
                    children.add(canvasLayer);
                });
    }

    private static ShapeElement createElement(final Shape shape) {
        final int layer = parseInt(shape.getPriority());
        final int id = shape.getId();
        final double x = parseDouble(shape.getX());
        final double y = parseDouble(shape.getY());
        final double width = parseDouble(shape.getWidth());
        final double height = parseDouble(shape.getHeight());
        final Color fillColor = ColorUtils.convert(shape.getColor());

        return switch (shape.getType()) {
            case ELLIPSE -> new EllipseElement(layer, id, x, y, width, height, fillColor);
            case RECT -> new RectangleElement(layer, id, x, y, width, height, fillColor);
        };
    }
}
