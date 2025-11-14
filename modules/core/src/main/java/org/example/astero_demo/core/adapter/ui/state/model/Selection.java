package org.example.astero_demo.core.adapter.ui.state.model;

import org.example.astero_demo.model.entity.ShapeType;

import java.util.stream.Stream;

public interface Selection {

    Integer getSelectedShapeId();

    Stream<Integer> getSelectedIds();

    boolean isIdSelected(final int id);

    Double getSelectedX();

    Double getSelectedY();

    Double getSelectedWidth();

    Double getSelectedHeight();

    Integer getSelectedLayer();

    Integer getSelectedColor();

    ShapeType getSelectedShapeType();

    boolean hasSelectedId();

    boolean isMultipleSelection();
}
