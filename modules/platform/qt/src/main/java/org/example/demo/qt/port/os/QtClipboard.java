package org.example.demo.qt.port.os;

import org.apache.commons.lang3.StringUtils;
import org.example.demo.core.port.os.OSClipboard;

/**
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtClipboard implements OSClipboard {

    @Override
    public void put(final String s) {
    }

    @Override
    public String get() {
        return StringUtils.EMPTY;
    }

    @Override
    public boolean hasCopy() {
        return false;
    }

    @Override
    public void clear() {}
}
