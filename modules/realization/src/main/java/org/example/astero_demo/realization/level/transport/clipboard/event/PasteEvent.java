package org.example.astero_demo.realization.level.transport.clipboard.event;

import lombok.Getter;

@Getter
public non-sealed class PasteEvent extends ClipboardEvent {
    private final double x;
    private final double y;

    public PasteEvent(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
}
