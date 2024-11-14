package org.example.astero_demo.adapter.ui.state;

public interface UIState {

    boolean isInInsertMode();

    Integer getSelectedShapeId();

    boolean hasSelectedId();

    Double getSelectedX();

    Double getSelectedY();

    Double getSelectedWidth();

    Double getSelectedHeight();

    Integer getSelectedLayer();

    Integer getSelectedColor();
}
