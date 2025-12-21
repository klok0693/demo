package org.example.astero_demo.swing.port.os;

import lombok.SneakyThrows;
import org.example.astero_demo.core.port.os.OSClipboard;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public class SwingClipboard implements OSClipboard {

    @Override
    public void put(final String s) {
        getClipboard().setContents(new StringSelection(s), null);
    }

    @Override
    @SneakyThrows
    public String get() {
        return (String) getClipboard().getData(DataFlavor.stringFlavor);
    }

    @Override
    public boolean hasCopy() {
        return getClipboard().isDataFlavorAvailable(DataFlavor.stringFlavor);
    }

    @Override
    public void clear() {
        getClipboard().setContents(null, null);
    }

    private Clipboard getClipboard() {
        return Toolkit.getDefaultToolkit().getSystemClipboard();
    }
}
