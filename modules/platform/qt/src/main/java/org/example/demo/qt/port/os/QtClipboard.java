package org.example.demo.qt.port.os;

import org.apache.commons.lang3.StringUtils;
import org.example.demo.core.port.os.OSClipboard;

public class QtClipboard implements OSClipboard {

    @Override
    public void put(final String s) {
/*        final ClipboardContent content = new ClipboardContent();
        content.putString(s);

        getClipboard().setContent(content);*/
    }

    @Override
    public String get() {
        return StringUtils.EMPTY;//getClipboard().getString();
    }

    @Override
    public boolean hasCopy() {
        return false;//getClipboard().hasString();
    }

    @Override
    public void clear() {
        //getClipboard().clear();
    }

/*    private Clipboard getClipboard() {
        return Clipboard.getSystemClipboard();
    }*/
}
