package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.example.astero_demo.model.metadata.ParamInfo;

/**
 * A class representing an event for modifying shape parameters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public class ModifyShapeEvent extends ParamEvent {
    private final int shapeId;

    public ModifyShapeEvent(final int shapeId, final ParamInfo... infos) {
        super(infos);
        this.shapeId = shapeId;
    }
}
