package org.example.astero_demo.adapter.ui.state;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.Shape;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UIStateHolder implements MutableUIState {

    @Getter
    private boolean isInInsertMode = false;

    private List<Integer> selectedShapes = new LinkedList<>();
    private final StateHolder shapeHolder;

    public UIStateHolder(final StateHolder shapeHolder) {
        this.shapeHolder = shapeHolder;
    }

    @Override
    public void setIsInInsertMode(final boolean isInInsertMode) {
        this.isInInsertMode = isInInsertMode;
    }

    @Override
    public Integer getSelectedShapeId() {
 /*       return Optional.ofNullable(selectedShapes).stream()
                .flatMap(Collection::stream)
                .map(ShapeElement::getModelRelatedId)
                .findFirst()
                .orElse(-1);*/

        //return getSelectedIntParam(Shape::getId);
        if (selectedShapes.isEmpty()) {
            return null;
        }
        return shapeHolder.getShape(selectedShapes.get(0)).getId();
    }

    @Override
    public Double getSelectedX() {
        return getSelectedDoubleParam(Shape::getX);
    }

    @Override
    public Double getSelectedY() {
        return getSelectedDoubleParam(Shape::getY);
    }

    @Override
    public Double getSelectedWidth() {
        return getSelectedDoubleParam(Shape::getWidth);
    }

    @Override
    public Double getSelectedHeight() {
        return getSelectedDoubleParam(Shape::getHeight);
    }

    @Override
    public Integer getSelectedLayer() {
        if (selectedShapes.isEmpty()) {
            return null;
        }
        return Integer.valueOf(
                shapeHolder.getShape(selectedShapes.get(0)).getPriority());
    }

    @Override
    public Integer getSelectedColor() {
        if (selectedShapes.isEmpty()) {
            return null;
        }
        final String color = shapeHolder.getShape(selectedShapes.get(0)).getColor();
        return StringUtils.isNotBlank(color) ? Integer.valueOf(color) : null;
    }

    @Override
    public boolean hasSelectedId() {
        return !selectedShapes.isEmpty();
    }


    @Override
    public void setSelectShape(final Integer id) {
        removeSelection();
        if (id != null) {
            selectedShapes.add(id);
        }
    }

    @Override
    public void removeSelection() {
        selectedShapes.clear();
    }

    private Double getSelectedDoubleParam(final Function<Shape, String> func) {
        if (selectedShapes.isEmpty()) {
            return null;
        }
        return Double.valueOf(func.apply(shapeHolder.getShape(selectedShapes.get(0))));
/*        return Optional.ofNullable(selectedShapes.get(0)).map(func).orElse(null);*/
    }
}
