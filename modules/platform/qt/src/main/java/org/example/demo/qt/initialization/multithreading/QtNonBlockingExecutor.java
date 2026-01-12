package org.example.demo.qt.initialization.multithreading;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.SneakyThrows;
import org.example.demo.qt.port.ui.QtMemoryView;
import org.example.demo.realization.level.async.NonBlockingForegroundExecutor;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtNonBlockingExecutor implements NonBlockingForegroundExecutor, QtMemoryView {
    //<editor-fold desc="ABI method's names">
    private static final String NATIVE_RUN_LATER_NAME = "ui_run_later";
    //</editor-fold>
    private MethodHandle runLaterHandle;
    private MemorySegment runnableStub;

    static final AtomicLong IDS = new AtomicLong();
    static final Long2ObjectMap<Runnable> RUNNABLES =
            Long2ObjectMaps.synchronize(new Long2ObjectOpenHashMap<>());

    @Override
    public void initialize() throws Throwable {
        final MethodHandle invokeHandle = createJavaHandle(
                "invokeRunnable",
                void.class,
                new Class[]{ long.class }
        );

        this.runnableStub =
                LINKER.upcallStub(
                        invokeHandle,
                        FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG),
                        Arena.global()
                );

        this.runLaterHandle = findNative(
                NATIVE_RUN_LATER_NAME,
                FunctionDescriptor.ofVoid(
                        ValueLayout.ADDRESS,
                        ValueLayout.JAVA_LONG
                ));
    }

    @Override
    @SneakyThrows
    public void execute(final Runnable runnable) {
        final long id = IDS.incrementAndGet();
        RUNNABLES.put(id, runnable);

        runLaterHandle.invoke(runnableStub, id);
    }

    public void invokeRunnable(final long id) {
        final Runnable runnable = RUNNABLES.remove(id);
        if (runnable != null) {
            runnable.run();
        }
    }
}
