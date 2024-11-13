package org.example.astero_demo.port.ui.canvas.element;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.port.ui.canvas.CanvasLayer;

import java.util.stream.Collectors;

public class ShapeLayer extends CanvasLayer<CanvasLayer<ShapeElement>> {
    private final GraphicsContext gc;

    public ShapeLayer(final GraphicsContext gc) {
        super(gc, 1);
        this.gc = gc;
    }

    public void update(final StateHolder holder) {
        removeAll();
        final var elements = holder.getShapes().stream()
                .map(shape -> new Object() {
                    RectangleElement drawable = new RectangleElement(
                            shape.getId(),
                        shape.getX(),
                        shape.getY(),
                        shape.getWidth(),
                        shape.getHeight(),
                        Color.GREEN);

                    int priority = shape.getPriority();
                })
                .collect(Collectors.groupingBy(obj -> obj.priority,
                        Collectors.mapping(obj -> obj.drawable, Collectors.toList())));

        elements.keySet().forEach(key -> children.add(new CanvasLayer<>(gc, key)));
        children.stream()
                .map(CanvasLayer.class::cast)
                .forEach(layer -> elements.get(layer.getPriority()).forEach(layer::add));
    }

    public ShapeElement elementAt(final double x, final double y) {
        return children.stream()
                .sorted()
                .flatMap(CanvasLayer::getChildren)
                .filter(elem -> elem.isInBounds(x, y))
                .findFirst()
                .orElse(null);
    }
}
