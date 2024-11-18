package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;

@Getter
public class ModifyShapeEvent extends ParamEvent {
    private final int shapeId;

/*    public ModifyShapeEvent(final int shapeId, final ShapeParam param, final String newValue) {
        this(shapeId, param, MathUtils.isDouble(newValue) ? Double.parseDouble(newValue) : Integer.parseInt(newValue));
    }*/

    public ModifyShapeEvent(final int shapeId, ParamInfo... infos) {
        super(infos);
        this.shapeId = shapeId;
    }
}
