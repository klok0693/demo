package org.example.astero_demo.adapter.model.state;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ModelStateInstance implements ModelState {
    private final HashMap<Integer, Shape> shapes = new HashMap<>(32);

    @Override
    public Shape getShape(final int id) {
        return shapes.get(id);
    }

    @Override
    public Stream<Shape> findShapes(final Predicate<Shape>... predicates) {
        return filterShapes(getShapes(), new LinkedList<>(Arrays.asList(predicates)));
    }

    @Override
    public void saveShape(final Shape shape) {
        shapes.put(shape.getId(), shape);
    }

    @Override
    public void removeShape(final int id) {
        shapes.remove(id);
    }

    @Override
    public Stream<Shape> getShapes() {
        return shapes.values().stream();
    }

    private static Stream<Shape> filterShapes(final Stream<Shape> shapeStream, final Queue<Predicate<Shape>> predicates) {
        if (predicates.isEmpty()) {
            return shapeStream;
        }
        final Predicate<Shape> predicate = predicates.poll();
        return filterShapes(shapeStream.filter(predicate), predicates);
    }

    private static boolean compareShape(final Shape shape, final ParamInfo info) {
        final Function<String, Boolean> comparator = shapeValue -> StringUtils.equals(shapeValue, info.getNewValue());
        return switch (info.getParam()) {
            case X -> comparator.apply(shape.getX());
            case Y -> comparator.apply(shape.getY());
            case WIDTH -> comparator.apply(shape.getWidth());
            case HEIGHT -> comparator.apply(shape.getHeight());
            case PRIORITY -> comparator.apply(shape.getPriority());
            case COLOR -> comparator.apply(shape.getColor());
            case TYPE -> shape.getType() == ShapeType.valueOf(info.getNewValue());
        };
    }
}
