package org.example.astero_demo.realization.level.transport.clipboard.event;

import lombok.Getter;
import org.example.astero_demo.model.entity.Shape;

public non-sealed class CopyEvent extends ClipboardEvent {
    @Getter
    private final Shape copy;

    public CopyEvent(final Shape copy) {
        this.copy = copy;
    }
}
