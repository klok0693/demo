package org.example.astero_demo.adapter.ui.state;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.model.state.ModelState;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.metadata.ParamInfo.create;

public class UIStateHolder implements MutableUIState {
    @Getter
    private ShapeType insertShapeType;

    @Getter
    private List<ParamInfo> copyParams;

    private final List<Integer> selectedShapes = new LinkedList<>();
    private final ModelState modelState;

    public UIStateHolder(final ModelState modelState) {
        this.modelState = modelState;
    }

    @Override
    public boolean isInInsertMode() {
        return insertShapeType != null;
    }

    @Override
    public void setInsertShapeType(final ShapeType type) {
        removeSelection();
        this.insertShapeType = type;
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
        return modelState.getShape(selectedShapes.getFirst()).getId();
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
                modelState.getShape(selectedShapes.getFirst()).getPriority());
    }

    @Override
    public Integer getSelectedColor() {
        if (selectedShapes.isEmpty()) {
            return null;
        }
        final String color = modelState.getShape(selectedShapes.getFirst()).getColor();
        return StringUtils.isNotBlank(color) ? Integer.valueOf(color) : null;
    }

    @Override
    public ShapeType getSelectedShapeType() {
        if (selectedShapes.isEmpty()) {
            return null;
        }
        return modelState.getShape(selectedShapes.getFirst()).getType();
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
        reset();
        if (id != null) {
            selectedShapes.add(id);
        }
    }

    @Override
    public void reset() {
        removeSelection();
        setInsertShapeType(null);
    }

    private void removeSelection() {
        selectedShapes.clear();
    }

    @Override
    public void storeCopyOf(final int originalId) {
        copyParams = new LinkedList<>();

        final Shape original = modelState.getShape(originalId);
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
        return Double.valueOf(func.apply(modelState.getShape(selectedShapes.getFirst())));
/*        return Optional.ofNullable(selectedShapes.get(0)).map(func).orElse(null);*/
    }
}
