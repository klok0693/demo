package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.example.astero_demo.adapter.model.ShapeType;

@Getter
public class CreateNewShapeEvent extends LogicEvent {
    private final int priority;
    private final double x, y, width, height;
    private final ShapeType type;

    public CreateNewShapeEvent(
            final double x,
            final double y,
            final double width,
            final double height,
            final ShapeType type) {
        this.priority = 2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }
}
