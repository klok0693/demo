package org.example.astero_demo.swing.initialization.multithreading.clipboard;

import org.example.astero_demo.core.port.os.OSClipboard;
import org.example.astero_demo.realization.level.async.AsynchWrapper;
import org.example.astero_demo.realization.level.async.BlockingForegroundExecutor;

public class SwingOSClipboardAsyncWrapper extends AsynchWrapper<OSClipboard> implements OSClipboard {

    public SwingOSClipboardAsyncWrapper(
            final OSClipboard wrappedElement,
            final BlockingForegroundExecutor executor) {
        super(wrappedElement, executor);
    }

    @Override
    public void put(String obj) {

    }

    @Override
    public String get() {
        return null;
    }

    @Override
    public boolean hasCopy() {
        return false;
    }

    @Override
    public void clear() {

    }
}
