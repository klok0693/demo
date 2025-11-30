package org.example.astero_demo.realization.level.transport.logic.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents an event for removing a shape with a specified shape ID.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@AllArgsConstructor
public non-sealed class RemoveShapeEvent extends LogicEvent {
    @Getter
    private final int shapeId;
}
