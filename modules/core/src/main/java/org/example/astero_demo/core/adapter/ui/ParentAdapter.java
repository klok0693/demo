package org.example.astero_demo.core.adapter.ui;

import org.example.astero_demo.core.adapter.ui.event.UIEvent;

/**
 * Functional interface for processing {@link UIEvent}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface ParentAdapter {

    void processEvent(UIEvent event);
}
