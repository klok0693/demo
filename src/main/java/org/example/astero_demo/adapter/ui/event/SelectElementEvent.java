package org.example.astero_demo.adapter.ui.event;

import lombok.Getter;

public class SelectElementEvent extends UIEvent {
    @Getter
    private final double x, y;

    public SelectElementEvent(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
}
