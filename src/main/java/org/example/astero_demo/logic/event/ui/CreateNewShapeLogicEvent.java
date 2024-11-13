package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.example.astero_demo.adapter.model.ShapeType;

@Getter
public class CreateNewShapeLogicEvent extends UILogicEvent {
    private final int priority;
    private final double x, y;
    private final ShapeType type;

    public CreateNewShapeLogicEvent(final int priority, final double x, final double y) {
        this.priority = priority;
        this.x = x;
        this.y = y;
        this.type = ShapeType.RECT;
    }
}
