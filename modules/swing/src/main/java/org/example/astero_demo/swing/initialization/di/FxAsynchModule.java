package org.example.astero_demo.swing.initialization.di;

import com.google.inject.*;
import org.example.astero_demo.core.port.os.OSClipboard;
import org.example.astero_demo.swing.initialization.multithreading.FxBlockingExecutor;
import org.example.astero_demo.swing.initialization.multithreading.FxNonBlockingExecutor;
import org.example.astero_demo.swing.initialization.multithreading.FxRunnableWrapper;
import org.example.astero_demo.swing.port.os.SwingClipboard;
import org.example.astero_demo.realization.level.async.BlockingForegroundExecutor;
import org.example.astero_demo.realization.level.async.RunnableWrapper;
import org.example.astero_demo.realization.level.async.NonBlockingForegroundExecutor;
import org.example.astero_demo.swing.initialization.multithreading.clipboard.OSClipboardAsyncWrapper;

class FxAsynchModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RunnableWrapper.class).to(FxRunnableWrapper.class).in(Scopes.SINGLETON);
        bind(NonBlockingForegroundExecutor.class).to(FxNonBlockingExecutor.class).in(Scopes.SINGLETON);
        bind(BlockingForegroundExecutor.class).to(FxBlockingExecutor.class).in(Scopes.SINGLETON);

        bind(OSClipboard.class).to(OSClipboardAsyncWrapper.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public OSClipboardAsyncWrapper provideOSClipboardAsyncWrapper(
            final SwingClipboard wrappedElement, final BlockingForegroundExecutor executor) {
        return new OSClipboardAsyncWrapper(wrappedElement, executor);
    }
}
