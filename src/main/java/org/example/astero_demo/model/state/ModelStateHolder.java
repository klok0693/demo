package org.example.astero_demo.model.state;

import org.example.astero_demo.model.entity.Shape;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * An application can manage multiple model states simultaneously.<p>
 * For instance, this might occur when the application supports opening<p>
 * several projects in different tabs at the same time. In such scenarios,<p>
 * it’s necessary to switch between these states. This class serves as a<p>
 * container for all states and implements the same interface as the state<p>
 * class, enabling it to be passed to components in place of an individual state.<p>
 * To switch states, the holder's current state is updated, and the adapters are<p>
 * refreshed. This approach ensures seamless state transitions without the adapters<p>
 * being directly affected.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public enum ModelStateHolder implements MutableModelState {
    INSTANCE(new ModelStateInstance());

    private final List<MutableModelState> instances;
    private MutableModelState currentState;

    ModelStateHolder(final MutableModelState state) {
        this.instances = Collections.singletonList(state);
        this.currentState = state;
    }

    @Override
    @Nullable
    public Shape getShape(final int id) {
        return Optional.ofNullable(currentState)
                .map(state -> state.getShape(id))
                .orElse(null);
    }

    @Override
    public Stream<Shape> findShapesAt(final double x, final double y) {
        return Optional.ofNullable(currentState)
                .map(state -> state.findShapesAt(x, y))
                .orElse(null);
    }

    @Override
    @Nullable
    public Shape findNextShapeAt(final int currentId, final double x, final double y) {
        return Optional.ofNullable(currentState)
                .map(state -> state.findNextShapeAt(currentId, x, y))
                .orElse(null);
    }


    @Override
    public Optional<Shape> findTopShapeAt(double x, double y) {
        return Optional.ofNullable(currentState)
                .map(state -> state.findTopShapeAt(x, y))
                .orElse(null);
    }

    @Override
    public Optional<Shape> findTopVisibleShape(double mouseX, double mouseY) {
        return Optional.ofNullable(currentState)
                .map(state -> state.findTopVisibleShape(mouseX, mouseY))
                .orElse(null);
    }


    @Override
    public Stream<Shape> findShapes(final Predicate<Shape>... predicates) {
        return Optional.ofNullable(currentState)
                .map(state -> state.findShapes(predicates))
                .orElse(null);
    }

    @Override
    public void saveShape(final Shape shape) {
        Optional.ofNullable(currentState).ifPresent(state -> state.saveShape(shape));
    }

    @Override
    public void removeShape(final int id) {
        Optional.ofNullable(currentState).ifPresent(state -> state.removeShape(id));
    }

    @Override
    public Stream<Shape> getShapes() {
        return Optional.ofNullable(currentState)
                .map(ModelState::getShapes)
                .orElse(null);
    }
}
