package org.example.astero_demo.adapter.ui.state;

import org.example.astero_demo.adapter.model.entity.ShapeType;

public interface UIState {

    boolean isInInsertMode();

    ShapeType getInsertShapeType();

    Integer getSelectedShapeId();

    boolean hasSelectedId();

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
