package org.example.astero_demo.adapter.model.state;

import org.example.astero_demo.adapter.model.entity.Shape;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface ModelState {

    Shape getShape(int id);

    Stream<Shape> findShapesAt(double x, double y);

    Optional<Shape> findTopShapeAt(double x, double y);

    Optional<Shape> findTopVisibleShape(double mouseX, double mouseY);

    Stream<Shape> findShapes(Predicate<Shape>... predicates);

    Stream<Shape> getShapes();
}
