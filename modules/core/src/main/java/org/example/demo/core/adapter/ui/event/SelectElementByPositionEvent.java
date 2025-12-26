package org.example.demo.core.adapter.ui.event;

import lombok.Getter;

/**
 * Event to select an element by its position on the canvas
 */
@Getter
public class SelectElementByPositionEvent extends UIEvent {
    private final double x;
    private final double y;

    public SelectElementByPositionEvent(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
}
