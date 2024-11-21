package org.example.astero_demo.adapter.model.state;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.valueOf;

public class ModelStateInstance implements MutableModelState {
    private final HashMap<Integer, Shape> shapes = new LinkedHashMap<>(32);

    @Override
    public Shape getShape(final int id) {
        return shapes.get(id);
    }

    @Override
    public Stream<Shape> findShapesAt(final double x, final double y) {
        return findShapes(shape -> shape.isInVisibleBounds(x, y));
    }

    @Override
    public Optional<Shape> findTopShapeAt(double x, double y) {
        return findShapesAt(x, y).reduce((first, second) -> second);
    }

    @Override
    public Shape findNextShapeAt(final int currentId, final double x, final double y) {
        final List<Shape> positionedShapes = findShapesAt(x, y)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
                }));

        return positionedShapes.stream()
                .filter(shape -> shape.isTheSame(currentId))
                .findFirst()
                .map(currentShape -> {
                    final int nextIndex = (positionedShapes.indexOf(currentShape) + 1) % positionedShapes.size();
                    return positionedShapes.get(nextIndex);
                })
                .orElse(null);
    }

    @Override
    public Optional<Shape> findTopVisibleShape(double mouseX, double mouseY) {
        return findShapes(shape -> shape.isInBounds(mouseX, mouseY)).reduce((first, second) -> second);
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
/*        final List<Shape> shapeList = new LinkedList<>(shapes.values());
        Collections.reverse(shapeList);
        return shapeList.stream();*/
        return shapes.values().stream().sorted(); //.sorted(Collections.reverseOrder());
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
            case ID -> comparator.apply(valueOf(shape.getId()));
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
