package org.example.astero_demo.adapter.model.state;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.entity.ShapeType;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum ModelStateHolder implements MutableModelState {
    INSTANCE(new ModelStateInstance());

    private final List<MutableModelState> instances;
    private MutableModelState currentState;

    ModelStateHolder(final MutableModelState state) {
        this.instances = Collections.singletonList(state);
        this.currentState = state;
    }

    @Override
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
