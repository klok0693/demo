package org.example.astero_demo.realization.level.async.ui;

import java.util.concurrent.Executor;

/**
 * Transferring execution from background synchronized part<p>
 * to asynchronized, like main UI thread, that can not be frozen
 */
@FunctionalInterface
public interface ForegroundExecutor extends Executor {
}
