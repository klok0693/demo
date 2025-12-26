package org.example.demo.realization.level.transport.logic.event;

import lombok.Getter;
import org.example.demo.model.metadata.dto.ShapeParams;

/**
 * Abstract class representing an event with shape parameters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public abstract sealed class ParamEvent extends LogicEvent
        permits CreateNewShapeEvent, ModifyShapeEvent {
    protected final ShapeParams shapeParams;

    protected ParamEvent(final ShapeParams shapeParams) {
        this.shapeParams = shapeParams;
    }
}
