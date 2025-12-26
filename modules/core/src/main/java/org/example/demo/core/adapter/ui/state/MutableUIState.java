package org.example.demo.core.adapter.ui.state;

import org.example.demo.core.adapter.ui.state.model.MutableSelection;
import org.example.demo.model.entity.ShapeType;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface MutableUIState extends UIState, MutableSelection {

    void setInsertShapeType(ShapeType type);

    void reset();
}
