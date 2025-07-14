package org.example.astero_demo.core.adapter.ui.event;

import lombok.Getter;

@Getter
public class SelectMultipleElementsEvent extends UIEvent {
    private final double x;
    private final double y;

    public SelectMultipleElementsEvent(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
}
