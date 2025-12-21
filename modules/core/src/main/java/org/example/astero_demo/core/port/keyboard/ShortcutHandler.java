package org.example.astero_demo.core.port.keyboard;

import org.example.astero_demo.api.keyboard.Key;

public interface ShortcutHandler {

    void process(Key key, boolean isControlDown);
}
