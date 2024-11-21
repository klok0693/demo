package org.example.astero_demo.adapter.ui.state;

import org.example.astero_demo.adapter.model.entity.ShapeType;

public interface MutableUIState extends UIState {

    void setInsertShapeType(ShapeType type);

    void setSelectShape(Integer id);

    void reset();

    void storeCopyOf(int originalId);
}
