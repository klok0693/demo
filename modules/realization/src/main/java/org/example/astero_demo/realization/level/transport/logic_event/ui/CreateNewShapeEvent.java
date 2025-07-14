package org.example.astero_demo.realization.level.transport.logic_event.ui;

import lombok.Getter;
import org.example.astero_demo.core.model.metadata.dto.ShapeParams;

/**
 * Represents an event for creating a new shape with specified parameters.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public class CreateNewShapeEvent extends ParamEvent {

    public CreateNewShapeEvent(final ShapeParams shapeParams) {
        super(shapeParams);
    }
}
