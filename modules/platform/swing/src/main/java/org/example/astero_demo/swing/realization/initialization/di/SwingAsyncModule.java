package org.example.astero_demo.swing.realization.initialization.di;

import com.google.inject.*;
import org.example.astero_demo.SwingHelloApplication;
import org.example.astero_demo.core.port.os.OSClipboard;
import org.example.astero_demo.HelloApplication;
import org.example.astero_demo.swing.realization.initialization.multithreading.SwingBlockingExecutor;
import org.example.astero_demo.swing.realization.initialization.multithreading.SwingNonBlockingExecutor;
import org.example.astero_demo.swing.realization.initialization.multithreading.SwingRunnableWrapper;
import org.example.astero_demo.swing.port.os.SwingClipboard;
import org.example.astero_demo.realization.level.async.BlockingForegroundExecutor;
import org.example.astero_demo.realization.level.async.RunnableWrapper;
import org.example.astero_demo.realization.level.async.NonBlockingForegroundExecutor;
import org.example.astero_demo.swing.realization.initialization.multithreading.clipboard.SwingOSClipboardAsyncWrapper;
import org.example.astero_demo.swing.realization.level.async.SwingHelloApplicationAsyncWrapper;

/**
 * DI config for async proxy
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */
class SwingAsyncModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RunnableWrapper.class).to(SwingRunnableWrapper.class).in(Scopes.SINGLETON);
        bind(NonBlockingForegroundExecutor.class).to(SwingNonBlockingExecutor.class).in(Scopes.SINGLETON);
        bind(BlockingForegroundExecutor.class).to(SwingBlockingExecutor.class).in(Scopes.SINGLETON);

        bind(OSClipboard.class).to(SwingOSClipboardAsyncWrapper.class).in(Scopes.SINGLETON);

        bind(HelloApplication.class).to(SwingHelloApplicationAsyncWrapper.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public SwingOSClipboardAsyncWrapper provideOSClipboardAsyncWrapper(
            final SwingClipboard wrappedElement, final BlockingForegroundExecutor executor) {
        return new SwingOSClipboardAsyncWrapper(wrappedElement, executor);
    }

    @Inject
    @Provides
    @Singleton
    public SwingHelloApplicationAsyncWrapper provideHelloApplicationAsyncWrapper(
            final SwingHelloApplication wrappedElement,
            final BlockingForegroundExecutor executor) {
        return new SwingHelloApplicationAsyncWrapper(wrappedElement, executor);
    }
}
