package org.example.astero_demo.logic.event.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class RemoveShapeEvent extends LogicEvent {
    @Getter
    private final int shapeId;
}
