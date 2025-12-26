package org.example.demo.core.port.keyboard;

import org.example.demo.api.keyboard.Key;

public interface ShortcutHandler {

    void process(Key key, boolean isControlDown);
}
