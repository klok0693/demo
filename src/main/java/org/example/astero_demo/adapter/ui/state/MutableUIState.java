package org.example.astero_demo.adapter.ui.state;

import org.example.astero_demo.adapter.model.entity.ShapeType;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface MutableUIState extends UIState {

    void setInsertShapeType(ShapeType type);

    void setSelectShape(Integer id);

    void reset();

    void storeCopyOf(int originalId);
}
