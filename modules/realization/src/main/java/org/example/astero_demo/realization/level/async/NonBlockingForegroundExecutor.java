package org.example.astero_demo.realization.level.async;

import java.util.concurrent.Executor;

/**
 * Transferring execution from background synchronized part<p>
 * to asynchronized, like main UI thread, that can not be frozen.<p>
 * The background thread aren't blocking, calling {@link #execute}<p>
 */
@FunctionalInterface
public interface NonBlockingForegroundExecutor extends Executor {
}
