package org.example.astero_demo.adapter.ui.state;

import org.example.astero_demo.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.adapter.ui.state.model.MutableSelection;
import org.example.astero_demo.model.entity.ShapeType;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface MutableUIState extends UIState, MutableSelection {

    void setInsertShapeType(ShapeType type);

    void setMode(UIMode mode);

    void reset();

    void storeCopyOf(int originalId);
}
