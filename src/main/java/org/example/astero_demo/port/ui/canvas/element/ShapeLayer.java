package org.example.astero_demo.port.ui.canvas.element;

import javafx.collections.WeakListChangeListener;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.util.ColorUtils;

import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ShapeLayer extends CanvasLayer<CanvasLayer<ShapeElement>> {
    private final ModelState modelState;

    public ShapeLayer(final GraphicsContext gc, final ModelState modelState) {
        super(1);
        this.modelState = modelState;

        children.addListener(new WeakListChangeListener<>(change -> {
            draw(gc);
        }));
    }

    public void update() {
        removeAll();
        modelState.getShapes()
                .map(ShapeLayer::createElement)
                .collect(Collectors.groupingBy(ShapeElement::getLayer, Collectors.toList()
                )).forEach((layer, elements) -> {
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
            case OVAL -> new OvalElement(layer, id, x, y, width, height, fillColor);
            case RECT -> new RectangleElement(layer, id, x, y, width, height, fillColor);
        };
    }
}
