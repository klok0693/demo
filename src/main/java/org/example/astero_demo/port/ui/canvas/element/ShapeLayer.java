package org.example.astero_demo.port.ui.canvas.element;

import javafx.collections.WeakListChangeListener;
import javafx.scene.canvas.GraphicsContext;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;
import org.example.astero_demo.util.ColorUtils;

import java.util.stream.Collectors;

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
        final var elements = modelState.getShapes()
                .map(shape -> new Object() {
                    ShapeElement drawable = createElement(shape);
                    int priority = Integer.valueOf(shape.getPriority());
                })
                .collect(Collectors.groupingBy(obj -> obj.priority,
                        Collectors.mapping(obj -> obj.drawable, Collectors.toList())));

        elements.keySet().forEach(key -> children.add(new CanvasLayer<>(key)));
        children.stream()
                .map(CanvasLayer.class::cast)
                .forEach(layer -> elements.get(layer.getPriority()).forEach(layer::add));
    }

    private ShapeElement createElement(final Shape shape) {
        return switch (shape.getType()) {
            case OVAL -> new OvalElement(
                    0,
                    shape.getId(),
                    Double.valueOf(shape.getX()),
                    Double.valueOf(shape.getY()),
                    Double.valueOf(shape.getWidth()),
                    Double.valueOf(shape.getHeight()),
                    ColorUtils.convert(Integer.valueOf(shape.getColor()))
            );
            case RECT -> new RectangleElement(
                    0,
                    shape.getId(),
                    Double.valueOf(shape.getX()),
                    Double.valueOf(shape.getY()),
                    Double.valueOf(shape.getWidth()),
                    Double.valueOf(shape.getHeight()),
                    ColorUtils.convert(Integer.valueOf(shape.getColor()))
            );
        };
    }
}
