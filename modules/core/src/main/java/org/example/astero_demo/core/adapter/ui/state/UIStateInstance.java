package org.example.astero_demo.core.adapter.ui.state;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.core.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.core.adapter.ui.state.model.SelectionHolder;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.entity.ShapeType;
import org.example.astero_demo.core.model.metadata.ParamInfo;
import org.example.astero_demo.core.model.metadata.ShapeParam;
import org.example.astero_demo.core.model.state.ModelState;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static org.example.astero_demo.core.model.metadata.ParamInfo.create;
import static org.example.astero_demo.util.logging.MarkerStorage.*;

/**
 * Realization of the State template fot the UI data
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class UIStateInstance implements MutableUIState {
    @Getter
    private ShapeType insertShapeType;

    @Getter
    private List<ParamInfo> copyParams;
    private final ModelState modelState;
    private UIMode mode;

    private final SelectionHolder selection;

    public UIStateInstance(final ModelState modelState) {
        this.modelState = modelState;
        this.selection = new SelectionHolder(modelState);
        this.mode = UIMode.SINGLE_SELECTION;
    }

    @Override
    public boolean isInInsertMode() {
        return this.isActiveMode(UIMode.INSERT);
    }

    @Override
    public UIMode getMode() {
        return this.mode;
    }

    @Override
    public boolean isActiveMode(final UIMode mode) {
        return this.mode == mode;
    }

    @Override
    public void setInsertShapeType(final ShapeType type) {
        reset();
        this.insertShapeType = type;
        this.mode = type == null ? UIMode.SINGLE_SELECTION : UIMode.INSERT;
        log.debug(UI_STATE_MARKER, "Set insertion shape type: {}", type);
    }

    @Override
    @Nullable
    public Integer getSelectedShapeId() {
        return selection.getSelectedShapeId();
    }

    @Override
    public Stream<Integer> getSelectedIds() {
        return selection.getSelectedIds();
    }

    @Override
    public boolean isIdSelected(final int id) {
        return selection.isIdSelected(id);
    }

    @Override
    @Nullable
    public Double getSelectedX() {
        return selection.getSelectedX();
    }

    @Override
    @Nullable
    public Double getSelectedY() {
        return selection.getSelectedY();
    }

    @Override
    @Nullable
    public Double getSelectedWidth() {
        return selection.getSelectedWidth();
    }

    @Override
    @Nullable
    public Double getSelectedHeight() {
        return selection.getSelectedHeight();
    }

    @Override
    @Nullable
    public Integer getSelectedLayer() {
        return selection.getSelectedLayer();
    }

    @Override
    @Nullable
    public Integer getSelectedColor() {
        return selection.getSelectedColor();
    }

    @Override
    @Nullable
    public ShapeType getSelectedShapeType() {
        return selection.getSelectedShapeType();
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
        return selection.hasSelectedId();
    }

    @Override
    public boolean isMultipleSelection() {
        return selection.isMultipleSelection();
    }

    @Override
    public void setSelectShape(final Integer id) {
        reset();
        selection.setSelectShape(id);
        this.mode = UIMode.SINGLE_SELECTION;
        log.debug(UI_STATE_MARKER, "Set selected shape id:{}", id);
    }

    @Override
    public void setMultipleSelectedShapes(final Integer... ids) {
        reset();
        selection.setMultipleSelectedShapes(ids);
        this.mode = UIMode.MULTIPLE_SELECTION;
        log.debug(UI_STATE_MARKER, "Set multiple selected shape ids:{}", List.of(ids));
    }

    @Override
    public void reset() {
        selection.removeSelection();
        log.debug(UI_STATE_MARKER, "Clear selection");

        this.insertShapeType = null;
        this.mode = UIMode.SINGLE_SELECTION;
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

        log.debug(UI_STATE_MARKER, "Save copy of {}", original);
    }
}
