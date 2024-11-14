package org.example.astero_demo.adapter.ui.state;

import lombok.Getter;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public enum UIStateHolder implements MutableUIState {
    INSTANCE;

    @Getter
    private boolean isInInsertMode = false;

    private List<ShapeElement> selectedShapes = new LinkedList<>();

    @Override
    public void setIsInInsertMode(final boolean isInInsertMode) {
        this.isInInsertMode = isInInsertMode;
    }

    @Override
    public int getSelectedShapeId() {
 /*       return Optional.ofNullable(selectedShapes).stream()
                .flatMap(Collection::stream)
                .map(ShapeElement::getModelRelatedId)
                .findFirst()
                .orElse(-1);*/

        return getSelectedIntParam(ShapeElement::getModelRelatedId);
    }

    @Override
    public Double getSelectedX() {
        return getSelectedDoubleParam(ShapeElement::getX);
    }

    @Override
    public Double getSelectedY() {
        return getSelectedDoubleParam(ShapeElement::getY);
    }

    @Override
    public Double getSelectedWidth() {
        return getSelectedDoubleParam(ShapeElement::getWidth);
    }

    @Override
    public Double getSelectedHeight() {
        return getSelectedDoubleParam(ShapeElement::getHeight);
    }

    @Override
    public Integer getSelectedLayer() {
        return getSelectedIntParam(ShapeElement::getLayer);
    }

    @Override
    public boolean hasSelectedId() {
        return !selectedShapes.isEmpty();
    }


    @Override
    public void setSelectShape(final ShapeElement shape) {
        removeSelection();
        if (shape != null) {
            selectedShapes.add(shape);
        }
    }

    @Override
    public void removeSelection() {
        selectedShapes.clear();
    }

    private Integer getSelectedIntParam(final Function<ShapeElement, Integer> func) {
        if (selectedShapes.isEmpty()) {
            return null;
        }
        return Optional.ofNullable(selectedShapes.get(0)).map(func).orElse(null);
    }

    private Double getSelectedDoubleParam(final Function<ShapeElement, Double> func) {
        if (selectedShapes.isEmpty()) {
            return null;
        }
        return Optional.ofNullable(selectedShapes.get(0)).map(func).orElse(null);
    }
}
