package org.example.astero_demo.realization.level.async.logic;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * {@link Executor} with virtual threads, used to switch heavy tasks to new background thread
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class BackgroundExecutor implements Executor {

    private static final String THREAD_NAME_PREFIX = "Background ";

    private final ThreadFactory virtualFactory = Thread.ofVirtual().factory();
    private final ExecutorService service;
    private final RunnableWrapper wrapper;

    public BackgroundExecutor(final RunnableWrapper wrapper) {
        this.wrapper = wrapper;
        this.service = Executors.newThreadPerTaskExecutor(runnable -> {
            final Thread thread = virtualFactory.newThread(runnable);
            thread.setName(THREAD_NAME_PREFIX + runnable.hashCode());
            return thread;
        });
    }

    @Override
    public void execute(final Runnable runnable) {
        service.execute(wrapper.wrap(runnable));
    }
}
