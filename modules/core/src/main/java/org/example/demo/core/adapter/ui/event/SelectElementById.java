package org.example.demo.core.adapter.ui.event;

import lombok.Getter;

/**
 * Event to select an element by its ID.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public class SelectElementById extends UIEvent {
    private final int selectedId;

    public SelectElementById(final int selectedId) {
        this.selectedId = selectedId;
    }
}
