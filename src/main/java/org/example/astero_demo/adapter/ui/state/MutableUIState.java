package org.example.astero_demo.adapter.ui.state;

import org.example.astero_demo.adapter.model.Shape;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.port.ui.canvas.element.ShapeElement;

public interface MutableUIState extends UIState {

    void setIsInInsertMode(boolean isInInsertMode);

    void setInsertShapeType(ShapeType type);

    void setSelectShape(Integer id);

    void removeSelection();
}
