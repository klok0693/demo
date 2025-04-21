package org.example.astero_demo.realization.level.react.logic_event.ui;

import lombok.Getter;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

/**
 * A class representing an event for modifying shape parameters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public class ModifyShapeEvent extends ParamEvent {
    private final int shapeId;

    public ModifyShapeEvent(final int shapeId, final ShapeParams shapeParams) {
        super(shapeParams);
        this.shapeId = shapeId;
    }
}
