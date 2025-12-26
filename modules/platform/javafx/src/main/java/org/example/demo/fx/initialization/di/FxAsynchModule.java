package org.example.demo.fx.initialization.di;

import com.google.inject.*;
import org.example.demo.core.port.os.OSClipboard;
import org.example.demo.fx.initialization.multithreading.FxBlockingExecutor;
import org.example.demo.fx.initialization.multithreading.FxNonBlockingExecutor;
import org.example.demo.fx.initialization.multithreading.FxRunnableWrapper;
import org.example.demo.fx.port.os.FxClipboard;
import org.example.demo.realization.level.async.BlockingForegroundExecutor;
import org.example.demo.realization.level.async.RunnableWrapper;
import org.example.demo.realization.level.async.NonBlockingForegroundExecutor;
import org.example.demo.fx.initialization.multithreading.clipboard.FxOSClipboardAsyncWrapper;

class FxAsynchModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RunnableWrapper.class).to(FxRunnableWrapper.class).in(Scopes.SINGLETON);
        bind(NonBlockingForegroundExecutor.class).to(FxNonBlockingExecutor.class).in(Scopes.SINGLETON);
        bind(BlockingForegroundExecutor.class).to(FxBlockingExecutor.class).in(Scopes.SINGLETON);

        bind(OSClipboard.class).to(FxOSClipboardAsyncWrapper.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public FxOSClipboardAsyncWrapper provideOSClipboardAsyncWrapper(
            final FxClipboard wrappedElement, final BlockingForegroundExecutor executor) {
        return new FxOSClipboardAsyncWrapper(wrappedElement, executor);
    }
}
