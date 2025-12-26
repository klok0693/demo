package org.example.demo.realization.level.transport.clipboard.event;

import lombok.Getter;

public non-sealed class CutEvent extends ClipboardEvent {
    @Getter
    private final int id;

    public CutEvent(final int id) {
        this.id = id;
    }
}
