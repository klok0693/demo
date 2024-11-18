package org.example.astero_demo.adapter.ui.event;

import lombok.Getter;

public class SelectElementByPositionEvent extends UIEvent {
    @Getter
    private final double x, y;

    public SelectElementByPositionEvent(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
}
