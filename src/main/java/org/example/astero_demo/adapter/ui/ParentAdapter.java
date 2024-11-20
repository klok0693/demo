package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.ui.event.UIEvent;

@FunctionalInterface
public interface ParentAdapter {

    void processEvent(UIEvent event);
}
