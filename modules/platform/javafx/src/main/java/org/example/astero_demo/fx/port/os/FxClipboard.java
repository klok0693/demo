package org.example.astero_demo.fx.port.os;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.example.astero_demo.core.port.os.OSClipboard;

public class FxClipboard implements OSClipboard {

    @Override
    public void put(final String s) {
        final ClipboardContent content = new ClipboardContent();
        content.putString(s);

        getClipboard().setContent(content);
    }

    @Override
    public String get() {
        return getClipboard().getString();
    }

    @Override
    public boolean hasCopy() {
        return getClipboard().hasString();
    }

    @Override
    public void clear() {
        getClipboard().clear();
    }

    private Clipboard getClipboard() {
        return Clipboard.getSystemClipboard();
    }
}
