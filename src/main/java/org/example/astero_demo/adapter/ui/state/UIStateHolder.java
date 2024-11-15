package org.example.astero_demo.adapter.ui.state;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.*;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.ParamInfo.create;

public class UIStateHolder implements MutableUIState {

    @Getter
    private boolean isInInsertMode = false;
    @Getter
    @Setter
    private ShapeType insertShapeType;

    @Getter
    private List<ParamInfo> copyParams;

    private List<Integer> selectedShapes = new LinkedList<>();
    private final StateHolder shapeHolder;

    public UIStateHolder(final StateHolder shapeHolder) {
        this.shapeHolder = shapeHolder;
    }

    @Override
    public void setIsInInsertMode(final boolean isInInsertMode) {
        this.isInInsertMode = isInInsertMode;
        if (!isInInsertMode) {
            this.insertShapeType = null;
        }
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
    public ShapeType getSelectedShapeType() {
        if (selectedShapes.isEmpty()) {
            return null;
        }
        return shapeHolder.getShape(selectedShapes.getFirst()).getType();
    }

    @Override
    public boolean hasCopy() {
        return copyParams != null && !copyParams.isEmpty();
    }

    @Override
    public String getCopyWidth() {
        return getCopyParam(ShapeParam.WIDTH);
    }

    @Override
    public String getCopyHeight() {
        return getCopyParam(ShapeParam.HEIGHT);
    }

    @Override
    public String getCopyPriority() {
        return getCopyParam(ShapeParam.PRIORITY);
    }

    @Override
    public String getCopyColor() {
        return getCopyParam(ShapeParam.COLOR);
    }

    @Override
    public String getCopyType() {
        return getCopyParam(ShapeParam.TYPE);
    }

    private String getCopyParam(final ShapeParam param) {
        if (copyParams == null || copyParams.isEmpty()) {
            return StringUtils.EMPTY;
        }
        return copyParams.stream()
                .filter(paramInfo -> paramInfo.getParam() == param)
                .map(ParamInfo::getNewValue)
                .findFirst()
                .orElse(StringUtils.EMPTY);
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

    @Override
    public void storeCopyOf(final int originalId) {
        copyParams = new LinkedList<>();

        final Shape original = shapeHolder.getShape(originalId);
        if (original == null) {
            return;
        }

        copyParams.add(create(ShapeParam.X, original.getX()));
        copyParams.add(create(ShapeParam.Y, original.getY()));
        copyParams.add(create(ShapeParam.WIDTH, original.getWidth()));
        copyParams.add(create(ShapeParam.HEIGHT, original.getHeight()));
        copyParams.add(create(ShapeParam.COLOR, original.getColor()));
        copyParams.add(create(ShapeParam.PRIORITY, original.getPriority()));
        copyParams.add(create(ShapeParam.TYPE, valueOf(original.getType())));
    }

    private Double getSelectedDoubleParam(final Function<Shape, String> func) {
        if (selectedShapes.isEmpty()) {
            return null;
        }
        return Double.valueOf(func.apply(shapeHolder.getShape(selectedShapes.get(0))));
/*        return Optional.ofNullable(selectedShapes.get(0)).map(func).orElse(null);*/
    }
}
