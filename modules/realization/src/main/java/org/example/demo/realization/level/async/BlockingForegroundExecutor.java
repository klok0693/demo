package org.example.demo.realization.level.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * Transferring execution from background synchronized part<p>
 * to asynchronized, like main UI thread, that can not be frozen.<p>
 * The background thread are blocking, calling {@link #execute}.<p>
 * Also, there are overloaded method, that can return a value from<p>
 * foreground thread
 */
public interface BlockingForegroundExecutor extends Executor {

    <T> T execute(Callable<T> supplier);
}
