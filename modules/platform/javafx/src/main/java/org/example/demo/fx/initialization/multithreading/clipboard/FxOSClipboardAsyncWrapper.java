package org.example.demo.fx.initialization.multithreading.clipboard;

import org.example.demo.core.port.os.OSClipboard;
import org.example.demo.realization.level.async.AsynchWrapper;
import org.example.demo.realization.level.async.BlockingForegroundExecutor;

public class FxOSClipboardAsyncWrapper
        extends AsynchWrapper<OSClipboard, BlockingForegroundExecutor>
        implements OSClipboard {

    public FxOSClipboardAsyncWrapper(
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
