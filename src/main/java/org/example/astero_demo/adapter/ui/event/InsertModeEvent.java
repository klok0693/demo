package org.example.astero_demo.adapter.ui.event;

import lombok.Getter;
import org.example.astero_demo.adapter.model.entity.ShapeType;

public class InsertModeEvent extends UIEvent {
    @Getter
    private final ShapeType insertShapeType;

    public InsertModeEvent(final ShapeType insertShapeType) {
        this.insertShapeType = insertShapeType;
    }
}
