package org.example.astero_demo.core.adapter.ui.event;

import lombok.Getter;

/**
 * Event to select the next element at a specific position on the canvas
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public class SelectNextElementAt extends UIEvent {
    private final int currentId;
    private final double x;
    private final double y;

    public SelectNextElementAt(final int currentId, final double x, final double y) {
        this.currentId = currentId;
        this.x = x;
        this.y = y;
    }
}
