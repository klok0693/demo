package org.example.astero_demo.adapter.ui.event;

import lombok.Getter;

public class SelectNextElementAt extends UIEvent {
    @Getter
    private final int currentId;
    @Getter
    private final double x, y;

    public SelectNextElementAt(final int currentId, final double x, final double y) {
        this.currentId = currentId;
        this.x = x;
        this.y = y;
    }
}
