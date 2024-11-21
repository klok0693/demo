package org.example.astero_demo.logic.event.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents an event for removing a shape with a specified shape ID.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@AllArgsConstructor
public class RemoveShapeEvent extends LogicEvent {
    @Getter
    private final int shapeId;
}
