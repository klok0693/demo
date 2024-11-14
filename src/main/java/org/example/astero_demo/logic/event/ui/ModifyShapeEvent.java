package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.example.astero_demo.adapter.model.ParamInfo;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.util.MathUtils;

import java.util.List;
import java.util.Map;

@Getter
public class ModifyShapeEvent extends LogicEvent {
    private final int shapeId;
    private ParamInfo[] paramInfos;

/*    public ModifyShapeEvent(final int shapeId, final ShapeParam param, final String newValue) {
        this(shapeId, param, MathUtils.isDouble(newValue) ? Double.parseDouble(newValue) : Integer.parseInt(newValue));
    }*/

    public ModifyShapeEvent(final int shapeId, ParamInfo... infos) {
        this.shapeId = shapeId;
        this.paramInfos = infos;
    }
}
