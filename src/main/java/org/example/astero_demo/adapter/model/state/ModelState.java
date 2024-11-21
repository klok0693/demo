package org.example.astero_demo.adapter.model.state;

import org.example.astero_demo.adapter.model.entity.Shape;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Current state of the model
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface ModelState {

    /**
     * Get {@link Shape} by given id
     */
    Shape getShape(int id);

    /**
     * Get all {@link Shape}, lying on a given point
     */
    Stream<Shape> findShapesAt(double x, double y);

    /**
     * The shapes can be superimposed one on top of the other. The method takes<p>
     * the {@link Shape} with the specified identifier as the starting shape and<p>
     * returns the next underlying form in the specified coordinates<p>
     * <p>
     * If there is only one shape at a given point, it will be returned. If there are no shapes, then null
     */
    Shape findNextShapeAt(int currentId, double x, double y);

    /**
     * Get top {@link Shape} at given point
     */
    Optional<Shape> findTopShapeAt(double x, double y);

    /**
     * In terms of implementation, the {@link Shape} is always rectangular. But visible part<p>
     * of the figure may differ from its dimensions. The method returns the shape at a given point<p>
     * based on the visible part of the shape
     */
    Optional<Shape> findTopVisibleShape(double mouseX, double mouseY);

    /**
     * Get all {@link Shape}, matching all given predicates
     */
    Stream<Shape> findShapes(Predicate<Shape>... predicates);

    Stream<Shape> getShapes();
}
