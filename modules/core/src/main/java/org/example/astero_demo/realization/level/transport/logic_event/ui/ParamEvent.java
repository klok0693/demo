package org.example.astero_demo.realization.level.transport.logic_event.ui;

import lombok.Getter;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

/**
 * Abstract class representing an event with shape parameters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public abstract class ParamEvent extends LogicEvent {
    protected final ShapeParams shapeParams;

    protected ParamEvent(final ShapeParams shapeParams) {
        this.shapeParams = shapeParams;
    }
}
