package org.example.demo.qt.port.ui;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

import static java.lang.invoke.MethodType.methodType;

/**
 * @author Pilip Yurchanka
 * @since v1.2
 */
public interface QtMemoryView {
    Linker LINKER = Linker.nativeLinker();
    SymbolLookup LOOKUP = createLookup();

    private static SymbolLookup createLookup() {
        try {
            return SymbolLookup.libraryLookup("libui.so", Arena.global());
        }
        catch (final Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    default MemorySegment bindMethodToNative(
            final String javaName,
            final Class<?> rtype,
            final Class<?>[] ptypes,
            final FunctionDescriptor upcallDescription,
            final String nativeRefName,
            final String nativeName
    ) throws Throwable {
        final MemorySegment segment =
                createMemorySegment(javaName, rtype, ptypes, upcallDescription);

        final MemorySegment qtRef =
                (MemorySegment) LINKER.downcallHandle(
                        LOOKUP.find(nativeRefName).orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.ADDRESS)
                ).invoke();

        final MethodHandle downcallHandle = findNative(
                nativeName,
                FunctionDescriptor.ofVoid(
                        ValueLayout.ADDRESS,
                        ValueLayout.ADDRESS,
                        ValueLayout.ADDRESS));

        downcallHandle.invoke(qtRef, segment, MemorySegment.NULL);

        return segment;
    }

    default MemorySegment createMemorySegment(
            final String javaName,
            final Class<?> rtype,
            final Class<?>[] ptypes,
            final FunctionDescriptor upcallDescription
    ) throws Throwable {
        try {
            return LINKER.upcallStub(
                    createJavaHandle(javaName, rtype, ptypes),
                    upcallDescription,
                    Arena.global());
        }
        catch (final Throwable e) {
            /**
             * Wrong FFM initialization can lead to SEGFAULT and jvm crash.
             * To help investigate the issue without digging into the crash dump file,
             * trouble output is printed to stdout stream
             */
            e.printStackTrace();
            throw e;
        }
    }

    default MethodHandle createJavaHandle(
            final String javaName,
            final Class<?> rtype,
            final Class<?>[] ptypes
    ) throws NoSuchMethodException, IllegalAccessException {

        return MethodHandles.lookup()
                .findVirtual(this.getClass(), javaName, methodType(rtype, ptypes))
                .bindTo(this);
    }

    /**
     * Java objects do not store native object's references, only
     * MethodHandler, that provide references
     */
    default MethodHandle findNative(
            final String nativeName,
            final FunctionDescriptor downcallDescription) {

        return LINKER.downcallHandle(
                LOOKUP.find(nativeName).orElseThrow(),
                downcallDescription
        );
    }

    void initialize() throws Throwable;
}
