package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.util.MathUtils;

@Getter
public class ModifyShapeEvent extends LogicEvent {
    private final int shapeId;
    private final ShapeParam param;
    private final String newValue;

/*    public ModifyShapeEvent(final int shapeId, final ShapeParam param, final String newValue) {
        this(shapeId, param, MathUtils.isDouble(newValue) ? Double.parseDouble(newValue) : Integer.parseInt(newValue));
    }*/

    public ModifyShapeEvent(final int shapeId, final ShapeParam param, final String newValue) {
        this.shapeId = shapeId;
        this.param = param;
        this.newValue = newValue;
    }
}
