package org.example.astero_demo.adapter.ui.state;

import org.example.astero_demo.model.entity.ShapeType;

import java.util.stream.Stream;

/**
 * In addition to the model's data, UI block has it own state,<p>
 * holding references on current selected state, copied values etc.<p>
 *
 * This state only reflects data from the model state, so it must be updated<p>
 * every time the model data values are changed
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface UIState {

    boolean isInInsertMode();

    ShapeType getInsertShapeType();

    Integer getSelectedShapeId();

    Stream<Integer> getSelectedIds();

    boolean hasSelectedId();

    boolean isIdSelected(int id);

    boolean isMultipleSelection();

    Double getSelectedX();

    Double getSelectedY();

    Double getSelectedWidth();

    Double getSelectedHeight();

    Integer getSelectedLayer();

    Integer getSelectedColor();

    ShapeType getSelectedShapeType();

    boolean hasCopy();

    String getCopyWidth();

    String getCopyHeight();

    String getCopyPriority();

    String getCopyColor();

    String getCopyType();
}
