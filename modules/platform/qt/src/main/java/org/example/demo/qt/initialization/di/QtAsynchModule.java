package org.example.demo.qt.initialization.di;

import com.google.inject.*;
import org.example.demo.core.port.os.OSClipboard;
import org.example.demo.qt.initialization.multithreading.QtBlockingExecutor;
import org.example.demo.qt.initialization.multithreading.QtNonBlockingExecutor;
import org.example.demo.qt.initialization.multithreading.QtRunnableWrapper;
import org.example.demo.qt.port.os.QtClipboard;
import org.example.demo.realization.level.async.BlockingForegroundExecutor;
import org.example.demo.realization.level.async.RunnableWrapper;
import org.example.demo.realization.level.async.NonBlockingForegroundExecutor;
import org.example.demo.qt.initialization.multithreading.clipboard.QtOSClipboardAsyncWrapper;

class QtAsynchModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RunnableWrapper.class).to(QtRunnableWrapper.class).in(Scopes.SINGLETON);
        bind(NonBlockingForegroundExecutor.class).to(QtNonBlockingExecutor.class).in(Scopes.SINGLETON);
        bind(BlockingForegroundExecutor.class).to(QtBlockingExecutor.class).in(Scopes.SINGLETON);

        bind(OSClipboard.class).to(QtOSClipboardAsyncWrapper.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public QtOSClipboardAsyncWrapper provideOSClipboardAsyncWrapper(
            final QtClipboard wrappedElement, final BlockingForegroundExecutor executor) {
        return new QtOSClipboardAsyncWrapper(wrappedElement, executor);
    }
}
