package org.example.astero_demo.adapter.ui.event;

import lombok.Getter;

public class SelectElementById extends UIEvent {
    @Getter
    private final int selectedId;

    public SelectElementById(final int selectedId) {
        this.selectedId = selectedId;
    }
}
