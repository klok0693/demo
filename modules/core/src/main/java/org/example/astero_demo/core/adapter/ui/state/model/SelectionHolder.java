package org.example.astero_demo.core.adapter.ui.state.model;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.entity.ShapeType;
import org.example.astero_demo.core.model.state.ModelState;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SelectionHolder implements MutableSelection {
    private final ModelState modelState;
    private final List<Integer> selectedShapes = new LinkedList<>();

    public SelectionHolder(final ModelState modelState) {
        this.modelState = modelState;
    }

    @Override
    @Nullable
    public Integer getSelectedShapeId() {
        if (selectedShapes.isEmpty() || isMultipleSelection()) {
            return null;
        }
        return selectedShapes.getFirst();
    }

    @Override
    public Stream<Integer> getSelectedIds() {
        return selectedShapes.stream();
    }

    @Override
    public boolean isIdSelected(final int id) {
        return selectedShapes.contains(id);
    }

    @Override
    @Nullable
    public Double getSelectedX() {
        return getSelectedDoubleParam(Shape::getX);
    }

    @Override
    @Nullable
    public Double getSelectedY() {
        return getSelectedDoubleParam(Shape::getY);
    }

    @Override
    @Nullable
    public Double getSelectedWidth() {
        return getSelectedDoubleParam(Shape::getWidth);
    }

    @Override
    @Nullable
    public Double getSelectedHeight() {
        return getSelectedDoubleParam(Shape::getHeight);
    }

    @Override
    @Nullable
    public Integer getSelectedLayer() {
        if (selectedShapes.isEmpty() || isMultipleSelection()) {
            return null;
        }
        return Integer.valueOf(modelState.getShape(selectedShapes.getFirst()).getPriority());
    }

    @Override
    @Nullable
    public Integer getSelectedColor() {
        if (selectedShapes.isEmpty() || isMultipleSelection()) {
            return null;
        }
        final String color = modelState.getShape(selectedShapes.getFirst()).getColor();
        return StringUtils.isNotBlank(color) ? Integer.valueOf(color) : null;
    }

    @Override
    @Nullable
    public ShapeType getSelectedShapeType() {
        if (selectedShapes.isEmpty() || isMultipleSelection()) {
            return null;
        }
        return modelState.getShape(selectedShapes.getFirst()).getType();
    }

    @Override
    public boolean hasSelectedId() {
        return !selectedShapes.isEmpty();
    }

    @Override
    public boolean isMultipleSelection() {
        return selectedShapes.size() > 1;
    }

    @Override
    public void setSelectShape(final Integer id) {
        if (id != null) {
            selectedShapes.add(id);
        }
    }

    @Override
    public void setMultipleSelectedShapes(final Integer... ids) {
        if (ids != null) {
            selectedShapes.addAll(List.of(ids));
        }
    }

    public void removeSelection() {
        selectedShapes.clear();
    }

    @Nullable
    private Double getSelectedDoubleParam(final Function<Shape, String> func) {
        if (selectedShapes.isEmpty() || isMultipleSelection()) {
            return null;
        }
        return Double.valueOf(func.apply(modelState.getShape(selectedShapes.getFirst())));
    }
}
