package org.example.astero_demo.core.adapter.ui.event;

import lombok.Getter;
import org.example.astero_demo.model.entity.ShapeType;

/**
 * Event to insert new shape
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public class InsertModeEvent extends UIEvent {
    private final ShapeType insertShapeType;

    public InsertModeEvent(final ShapeType insertShapeType) {
        this.insertShapeType = insertShapeType;
    }
}
