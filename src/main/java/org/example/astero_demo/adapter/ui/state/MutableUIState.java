package org.example.astero_demo.adapter.ui.state;

public interface MutableUIState extends UIState {

    void setIsInInsertMode(boolean isInInsertMode);

    void setSelectShape(int shapeId);

    void removeSelection();
}
