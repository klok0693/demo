package org.example.astero_demo.swing.initialization.di;

import com.google.inject.*;
import org.example.astero_demo.core.port.os.OSClipboard;
import org.example.astero_demo.swing.initialization.multithreading.SwingBlockingExecutor;
import org.example.astero_demo.swing.initialization.multithreading.SwingNonBlockingExecutor;
import org.example.astero_demo.swing.initialization.multithreading.SwingRunnableWrapper;
import org.example.astero_demo.swing.port.os.SwingClipboard;
import org.example.astero_demo.realization.level.async.BlockingForegroundExecutor;
import org.example.astero_demo.realization.level.async.RunnableWrapper;
import org.example.astero_demo.realization.level.async.NonBlockingForegroundExecutor;
import org.example.astero_demo.swing.initialization.multithreading.clipboard.SwingOSClipboardAsyncWrapper;

class SwingAsynchModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RunnableWrapper.class).to(SwingRunnableWrapper.class).in(Scopes.SINGLETON);
        bind(NonBlockingForegroundExecutor.class).to(SwingNonBlockingExecutor.class).in(Scopes.SINGLETON);
        bind(BlockingForegroundExecutor.class).to(SwingBlockingExecutor.class).in(Scopes.SINGLETON);

        bind(OSClipboard.class).to(SwingOSClipboardAsyncWrapper.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public SwingOSClipboardAsyncWrapper provideOSClipboardAsyncWrapper(
            final SwingClipboard wrappedElement, final BlockingForegroundExecutor executor) {
        return new SwingOSClipboardAsyncWrapper(wrappedElement, executor);
    }
}
